package com.core.timmy.data.model;

import java.io.Serializable;

import java.util.List;

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
@Table(name = "CUSTOMER") //Preferible usar librerias más cercanas a java como jakarta


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // Nos crea el constructor vacio
@ToString
@Slf4j
public class Customer extends PersonOfInterest implements Serializable { //Hibernate obliga a poner Serializable

	
	private static final long serialVersionUID = 1L;
	
	/*@Id
	private Long id;
	
	@OneToOne
	@MapsId
	@JoinColumn(name="personOfInterest_id", referencedColumnName="id")
	private PersonOfInterest personOfInterest; //tiene que coincidir con el nombre del mapeo que le dimos en person of interest*/
	

	@Column(columnDefinition="CHAR(20)" + "CHECK(ACCOUNTNUMBER REGEXP '^[0-9]{0,20}')") //Esto solo funciona en la base de datos es una constraint para la base de datos
	@Size(max=20, message="{model.data.validation.Provider.accountNumber}")//Cif must be from 2 to 9 characters
	private String accountNumber;
	
	

	public Customer(Long id, String name, String cif, String vatnumber, String address, String phone, String email,
			List<Contact> contactList,
			String accountNumber) {
		super(id, name, cif, vatnumber, address, phone, email,contactList);
		this.accountNumber = accountNumber;
	}

	
	//Este no incopora los del hijo solo son los del padres
	/*public Customer(Long id, String name, String cif, String vatnumber, String address, String phone) {
		super(id, name, cif, vatnumber, address, phone);
		// TODO Auto-generated constructor stub
	}*/
	
	
	
	
	
	
	
}
