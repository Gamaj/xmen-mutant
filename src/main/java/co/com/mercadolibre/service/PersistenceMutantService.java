package co.com.mercadolibre.service;

import co.com.mercadolibre.dto.StatisticsDTO;

public interface PersistenceMutantService {
	
	//Retorna estadisticas de los datos
	StatisticsDTO getStatistics();
	
	//Registra el adn 
	void saveDNA(String dna, boolean isMutant);

	//Limpia los registros de la base de datos 
	void clean();

}
