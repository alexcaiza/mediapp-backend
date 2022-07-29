package com.mitocode.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Consult {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConsult;
	
	@Column(nullable = false, length = 3)	
	private String numConsult;
	
	@ManyToOne
    @JoinColumn(name = "id_patient", nullable = false, foreignKey = @ForeignKey(name = "FK_CONSULT_PATIENT"))
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "id_medic", nullable = false, foreignKey = @ForeignKey(name = "FK_CONSULT_MEDIC"))
    private Medic medic;

    @ManyToOne
    @JoinColumn(name = "id_specialty", nullable = false, foreignKey = @ForeignKey(name = "FK_CONSULT_SPECIALTY"))
    private Specialty specialty; 
	
	@Column(nullable = false)	
	private LocalDateTime consultDate;
	
	@OneToMany(mappedBy = "consult", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<ConsultDetail> details;
	
	
}
