package com.core.timmy.data.model;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*Esto a través de entity se convierte en una clase especial que va a funcionar como espejo, va tener que tener 
una contra parte en una base de datos.*/
import lombok.extern.slf4j.Slf4j;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // Nos crea el constructor vacio
@ToString
@Slf4j
public class PersonOfInterest implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@PositiveOrZero
	private Long id;
	
	@NotNull
	@Column(nullable=false, columnDefinition="VARCHAR(150) CHECK(LENGTH(NAME) >= 10)")//queremos que la columna no sea anulable . Si usamos name="nombre" se le asigna este nombre a esta columna name. en la tabla
	@Size(min=10,max=150, message="{model.data.validation.PersonOfInterest.name}") //Name must be from 10 to 150 characters
	private String name;
	
	@Column(columnDefinition="VARCHAR(30) CHECK(LENGTH(CIF) >= 9)")
	@Size(min=9,max=30, message="{model.data.validation.PersonOfInterest.cif}")//Cif must be from 9 to 30 characters
	private String cif;
	
	@Column(columnDefinition="VARCHAR(30) CHECK(LENGTH(VATNUMBER) >= 11)")
	@Size(min=11,max=30, message="{model.data.validation.PersonOfInterest.vatNumber}")//VatNumber must be from 11 to 30 characters
	private String vatnumber;
	
	@Column(columnDefinition="VARCHAR(520) DEFAULT 'TBD'")
	@Size(max=520, message="{model.data.validation.PersonOfInterest.address")// Address must have up to 520 characters
	private String address;
	
	@Column(columnDefinition="CHAR(18) CHECK(LENGTH(VATNUMBER) >= 9)")
	@Size(min=9 ,max=20, message="{model.data.validation.PersonOfInterest.phone}") //Phone must from 9 to 20 characters
	@NotNull
	private String phone; 
	
	
	
	
	
	
	
	
}
