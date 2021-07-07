package co.com.mercadolibre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.mercadolibre.dto.StatisticsDTO;
import co.com.mercadolibre.model.VerifiedMutant;
import co.com.mercadolibre.respository.VerifiedMutantRepository;

@Service
public class PersistenceMutantServiceImpl implements PersistenceMutantService {

	@Autowired
	private VerifiedMutantRepository verifiedMutantRepository;

	@Override
	public StatisticsDTO getStatistics() {
		// TODO Auto-generated method stub
		long human = verifiedMutantRepository.countHuman();
		long mutant = verifiedMutantRepository.countMutant();
		StatisticsDTO response = new StatisticsDTO();
		response.setCount_human_dna(human);
		response.setCount_mutant_dna(mutant);
		if (response.getCount_human_dna() > 0) {
			Double ratio = ((double)response.getCount_mutant_dna() / (double)response.getCount_human_dna());
			response.setRatio(ratio);
		} else {
			response.setRatio(0.0);
		}

		return response;
	}

	@Override
	public void saveDNA(String dna, boolean isMutant) {
		// TODO Auto-generated method stub
		//Verifica que no exista para guardarlo 
		if (!verifiedMutantRepository.existsById(dna)) {
			VerifiedMutant verifiedMutant = new VerifiedMutant();
			verifiedMutant.setDna(dna);
			verifiedMutant.setMutant(isMutant);
			verifiedMutantRepository.save(verifiedMutant);
		}
	}
	
	@Override
	public void clean() {
		//Elimina los datos existentes 
		verifiedMutantRepository.deleteAll();
	}

}
