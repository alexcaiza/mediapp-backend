package com.mitocode.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;


@EqualsAndHashCode
@Embeddable
public class ConsultExamPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@ManyToOne
	@JoinColumn(name= "id_consult")
	private Consult consult;
	
	@EqualsAndHashCode.Include
	@ManyToOne
	@JoinColumn(name= "id_exam")
	private Exam exam;
}
