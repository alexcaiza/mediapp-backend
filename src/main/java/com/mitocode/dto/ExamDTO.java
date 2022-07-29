package com.mitocode.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ExamDTO {
    
	@EqualsAndHashCode.Include
	private Integer idExam;
	
	@NotNull
	private String name;
	
	@NotNull
	private String description;
}
