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
public class Patient {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPatient;

    @Column(length = 70, nullable = false)
    private String firstName;

    @Column(length = 70, nullable = false)
    private String lastName;

    @Column(length = 8, nullable = false)
    private String dni;

    @Column(length = 150, nullable = true)
    private String address;

    @Column(length = 9, nullable = false)
    private String phone;

    @Column(length = 55, nullable = false)
    private String email;
}
