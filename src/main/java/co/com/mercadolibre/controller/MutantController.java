package co.com.mercadolibre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.mercadolibre.dto.DNARequestDTO;
import co.com.mercadolibre.dto.StatisticsDTO;
import co.com.mercadolibre.service.MutantService;
import co.com.mercadolibre.service.PersistenceMutantService;

@RestController
@RequestMapping("/mutant/")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET }, allowedHeaders = "*", exposedHeaders = "X-Api-Key")
public class MutantController {
	
	@Autowired
	private MutantService mutantService;
	
	@Autowired
	private PersistenceMutantService persistenceMutantService;
	
	//Controlador para evaluar si una cadena es mutante
	@GetMapping
	public ResponseEntity<Boolean> isMutant(@RequestParam String dna){
		
		return ResponseEntity.ok().body(mutantService.isMutant(dna));
	}
	
	//Controlador que devuelve las estadisticas de humanos vs mutantes
	@GetMapping("stats")
	public ResponseEntity<StatisticsDTO> stats(){
		
		return ResponseEntity.ok().body(persistenceMutantService.getStatistics());
	}
	
	//Controlador que recibe y procesa la matriz de adn para verificar si es mutante
	@PostMapping
	public ResponseEntity<String> isMutant(@RequestBody @Valid DNARequestDTO body){
		try {
			if (mutantService.matrixIsMutant(body.getDna(),body.getDna().size())) {
				persistenceMutantService.saveDNA(body.toString(), true);
				return ResponseEntity.ok().body("Mutante");
			}else {
				persistenceMutantService.saveDNA(body.toString(), false);
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No-Mutante");
			}

		}catch (Exception e) {
			if (e.getMessage().equals("Error la matriz debe ser cuadrada")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	//Controlador que limpia la base de datos
	@DeleteMapping
	public ResponseEntity<String> clean(){
		persistenceMutantService.clean();
		return ResponseEntity.ok().body("");
	}

}
