package co.com.mercadolibre.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class MutantServiceImpl implements MutantService {

	@Override
	public boolean isMutant(String dna) {
		// TODO Auto-generated method stub
		//Regex de validacion
		if (dna.matches(
				"(([ATCG]*)?A{4}([ATCG]*)?)|(([ATCG]*)?T{4}([ATCG]*)?)|(([ATCG]*)?G{4}([ATCG]*)?)|(([ATCG]*)?C{4}([ATCG]*)?)")) {
			return true;
		}

		return false;
	}

	//Evaluacion de matriz
	@Override
	public boolean matrixIsMutant(List<String> dna, Integer n) throws Exception {
		//Mapa para almacenamiento de columnas
		Map<Integer, String> columns = new HashMap<Integer, String>();
		String diagonalMiddle = "";
		for (int i = 0; i < n; i++) {
			String e = dna.get(i);
			if (e.length() == n) {
				//Evaluacion de fila
				if (isMutant(e)) {
					return true;
				}
				String diagonalTop = "";
				String diagonalBottom = "";
				for (int j = 0; j < n; j++) {
					if (i == 0) {
						columns.put(j, e.charAt(j) + "");
					} else {
						columns.replace(j, columns.get(j) + e.charAt(j));
					}
					if (j == i) {
						diagonalMiddle = diagonalMiddle + e.charAt(j);
					}
					if (j > i) {
						if (j - (i + 1) > 0) {
							String x = dna.get(j - (i + 1));
							diagonalTop = diagonalTop + x.charAt(j);
						} else {
							String x = dna.get(0);
							diagonalTop = diagonalTop + x.charAt(j);
						}
					} else if (j < i) {
						if (n - (i - j) > 0 && n - (i - j) < n) {
							String x = dna.get(n - (i - j));
							diagonalBottom = diagonalBottom + x.charAt(j);
						} else {
							String x = dna.get(n - 1);
							diagonalBottom = diagonalBottom + x.charAt(j);
						}
					}

				}
				//Evaluacion de diagonales superiores
				if (isMutant(diagonalTop)) {
					return true;
				}
				//Evaluacion de diagonales inferiores
				if (isMutant(diagonalBottom)) {
					return true;
				}


			} else {
				throw new Exception("Error la matriz debe ser cuadrada");
			}
		}
		//Evaluacion de diagonales media
		if (isMutant(diagonalMiddle)) {
			return true;
		}
		for (int i = 0; i < n; i++) {
			if (columns.get(i).length() == n) {
				//Evaluacion de columnas
				if (isMutant(columns.get(i))) {
					return true;
				}
			} else {
				return false;
			}

		}
		return false;
	}

}
