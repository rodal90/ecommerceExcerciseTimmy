package com.core.timmy.data.model;

import java.io.Serializable;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "CUSTOMER") //Preferible usar librerias más cercanas a java como jakarta


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // Nos crea el constructor vacio
@ToString
@Slf4j
public class Customer implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@PositiveOrZero
	private Long id;
	
	@OneToMany(mappedBy ="customer")
    private List<CustomerContact> customerContactList;
	//las etiquetas solo afecta a el elemento justo debajo de la etiqueta
	@NotNull
	@Column(nullable=false, columnDefinition="VARCHAR(150) CHECK(LENGTH(NAME) >= 10)")//queremos que la columna no sea anulable . Si usamos name="nombre" se le asigna este nombre a esta columna name. en la tabla
	@Size(min=10,max=150, message="{model.data.validation.Customer.name}") //Name must be from 10 to 150 characters
	private String name;
	
	@Column(columnDefinition="VARCHAR(30) CHECK(LENGTH(VATNUMBER) >= 11)")
	@Size(min=11,max=30, message="{model.data.validation.Customer.vatNumber}")//VatNumber must be from 11 to 30 characters
	private String vatnumber;
	
	@Column(columnDefinition="VARCHAR(520) DEFAULT 'TBD'")
	@Size(max=520, message="{model.data.validation.Customer.address")// Address must have up to 520 characters
	private String address;
	
	@Column(columnDefinition="CHAR(18) CHECK(LENGTH(VATNUMBER) >= 9)")
	@Size(min=9 ,max=20, message="{model.data.validation.Customer.phone}") //Phone must from 9 to 20 characters
	@NotNull
	private String phone; 
	
	
	
	
	
	
}
