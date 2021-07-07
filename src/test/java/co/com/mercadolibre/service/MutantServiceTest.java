package co.com.mercadolibre.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MutantServiceTest {

	@Autowired
	MutantService mutantService;

	@Test
	void isMutant() throws Exception {

		assertEquals(true, mutantService.isMutant("AAAATTGGCC"));
		assertEquals(false, mutantService.isMutant("ATCGCCGGTTAA"));
	}

	@Test
	void matrixIsMutant() throws Exception {
		
		assertEquals(true, mutantService.matrixIsMutant(List.of("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"), 6));
		assertEquals(false, mutantService.matrixIsMutant(List.of("ATGCGA","CAGTTC","TTATGT","AGACGG","CCTCTA","TCACTG"), 6));
		try {
			assertEquals(false, mutantService.matrixIsMutant(List.of("ATGCG","CAGTTC","TTATGT","AGACGG","CCTCTA","TCACTG"), 6));
		}catch (Exception e) {
			assertEquals(e.getMessage(),"Error la matriz debe ser cuadrada");
		}
	}
}
