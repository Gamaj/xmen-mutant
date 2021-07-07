package co.com.mercadolibre.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import co.com.mercadolibre.dto.StatisticsDTO;
import co.com.mercadolibre.service.MutantService;
import co.com.mercadolibre.service.PersistenceMutantService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MutantControllerTest {
	@LocalServerPort
	private int port;

	private String base_url;

	@MockBean
	private MutantService mutantService;

	@MockBean
	private PersistenceMutantService persistenceMutantService;

	@BeforeEach
	void setup() throws Exception {
		base_url = "http://localhost:" + port + "/rest-service/mutant/";
		StatisticsDTO stDTO = new StatisticsDTO();
		stDTO.setCount_human_dna(0L);
		stDTO.setCount_mutant_dna(0L);
		stDTO.setRatio(0.0);
		Mockito.when(mutantService.isMutant(Mockito.anyString())).thenReturn(true);
		Mockito.when(mutantService.matrixIsMutant(Mockito.anyList(), Mockito.anyInt())).thenReturn(true, false);
		Mockito.when(persistenceMutantService.getStatistics()).thenReturn(stDTO);

	}

	@Test
	void isMutant() {

		RestTemplate rt = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(base_url).queryParam("dna", "AAATTTGGGCCC");
		ResponseEntity<Boolean> response = rt.getForEntity(builder.toUriString(), Boolean.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(true, response.getBody());
	}

	@Test
	public void isMutantPost() {
		URI url = URI.create(base_url);

		RestTemplate rt = new RestTemplate();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("dna", List.of("AATTGG", "AATTGG", "AATTGG", "AATTGG", "AATTGG", "AATTGG"));
		RequestEntity<Map<String, List<String>>> request = RequestEntity.post(url).accept(MediaType.APPLICATION_JSON)
				.body(map);
		ResponseEntity<String> response = rt.exchange(request, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		try {
			response = rt.exchange(request, String.class);
		}catch(HttpClientErrorException e) {
			assertEquals(HttpStatus.FORBIDDEN, e.getStatusCode());
		}
	}
	
	@Test
	void stats() {

		RestTemplate rt = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(base_url+"stats");
		ResponseEntity<String> response = rt.getForEntity(builder.toUriString(), String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
