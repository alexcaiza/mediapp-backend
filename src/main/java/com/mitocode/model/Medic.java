package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Medic {
 
	//SELECT * FROM MEDICO WHERE IDMEDIC0 = ?
	//FROM Medic m WHERE m.idMedico = ?
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMedic;
	
	@Column(nullable = false, length = 70)	
	private String firstName;
	
	@Column(nullable = false, length = 70)
	private String lastName;
	
	@Column(nullable = false, length = 70, unique = true)
	private String cmp;
	
	private String photoUrl;
	
	private byte[] file;
}
