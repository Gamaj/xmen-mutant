package co.com.mercadolibre.service;

import java.util.List;

public interface MutantService {

	//Funcion nivel 1
	boolean isMutant(String dna);

	//Funcion nivel 2 evaluacuion de matriz
	boolean matrixIsMutant(List<String> dna, Integer n) throws Exception;

}
