package co.com.mercadolibre.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class DNARequestDTO {
	
	@NotNull
	private List<@Pattern(regexp = "[ATCG]+", message = "Solo puede enviar caracteres ATCG") @NotBlank String> dna;

	public List<String> getDna() {
		return dna;
	}

	public void setDna(List<String> dna) {
		this.dna = dna;
	}
	

	@Override
	public String toString() {
		
		return "DNARequestDTO=[ dna="+dna+"]";
		
	}

}
