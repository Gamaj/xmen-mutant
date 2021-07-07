package co.com.mercadolibre.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "registro")
public class VerifiedMutant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String dna;
	private boolean mutant;

	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}

	public boolean isMutant() {
		return mutant;
	}

	public void setMutant(boolean mutant) {
		this.mutant = mutant;
	}

	@Override
	public String toString() {
		return "VerifiedMutant=[" + "dna=" + dna + " ,mutant=" + mutant + "]";
	}

}
