package com.mitocode.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConsultDTO {
    
	@EqualsAndHashCode.Include
	private Integer idConsult;
	
	@NotNull
	private PatientDTO patient;
	
	@NotNull
	private MedicDTO medic;
		
	@NotNull
	private SpecialtyDTO specialty;
	
	@NotNull
	private String numConsult;
	
	@NotNull
	private LocalDateTime consultDate;
	
	@NotNull
	private List<ConsultDetailDTO> details;
	
}
