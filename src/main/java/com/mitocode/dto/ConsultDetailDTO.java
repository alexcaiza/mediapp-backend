package com.mitocode.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConsultDetailDTO {
	
	@EqualsAndHashCode.Include
	private Integer idDetail;
	
	@JsonIgnore
	// Escritura ilimitada
	// Que no se exprese en el json
	// Se ignara a nivel de request y response
    private ConsultDTO consult;
	
	@NotNull
    private String diagnosis;

	@NotNull
    private String treatment;

}
