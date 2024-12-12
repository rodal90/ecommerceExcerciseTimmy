package com.core.timmy.data.model;

import java.io.Serializable;

import java.util.List;

import com.core.timmy.data.validation.INifConstraint;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "PROVIDER") //Preferible usar librerias más cercanas a java como jakarta


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // Nos crea el constructor vacio
@ToString
@Slf4j
public class Provider extends PersonOfInterest implements Serializable {//Hibernate obliga a poner Serializable

	
	private static final long serialVersionUID = 1L;
	

	
	/*@Id
	private Long id;
	
	@OneToOne
	@MapsId
	@JoinColumn(name="personOfInterest_id", referencedColumnName="id")
	private PersonOfInterest personOfInterest; //tiene que coincidir con el nombre del mapeo que le dimos en person of interest*/
	
	
	@INifConstraint
	@Column(columnDefinition="VARCHAR(9) CHECK(LENGTH(NIF) >= 2) CHECK(NIF REGEXP '^[0-9]{1,8}[A-Za-z]{1}')") //Esto solo funciona en la base de datos es una constraint para la base de datos
	@Size(min=2,max=9, message="{model.data.validation.Provider.nif}")//Cif must be from 2 to 9 characters
	private String nif;



	public Provider(Long id, String name, String cif, String vatnumber, String address, String phone, String email,
			List<Contact>contactList,
			 String nif
			) {
		super(id, name, cif, vatnumber, address, phone, email, contactList);
		this.nif = nif;
	}
	
	
	
	
}
