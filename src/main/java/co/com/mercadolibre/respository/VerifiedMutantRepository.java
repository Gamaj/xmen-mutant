package co.com.mercadolibre.respository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.com.mercadolibre.model.VerifiedMutant;

public interface VerifiedMutantRepository extends CrudRepository<VerifiedMutant, String> {

	@Query("SELECT COUNT(m) FROM VerifiedMutant m WHERE m.mutant = true")
	long countMutant();
	
	@Query("SELECT COUNT(m) FROM VerifiedMutant m WHERE m.mutant = false")
	long countHuman();
}
