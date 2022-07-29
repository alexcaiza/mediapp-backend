package com.mitocode.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mitocode.model.Exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConsultListExamDTO {
	
	@NotNull
	@JsonProperty(value="consult")
	ConsultDTO consult;
	
	@NotNull
	List<Exam> lstExam;

}
