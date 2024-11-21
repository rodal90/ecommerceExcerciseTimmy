package com.core.timmy.data.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
@Table(name = "CUSTOMER_CONTACT") //Preferible usar librerias más cercanas a java como jakarta


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // Nos crea el constructor vacio
@ToString
@Slf4j

public class CustomerContact implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Positive
	private Long id;
	
	
	
	@NotNull
	@Column(nullable=false, columnDefinition="VARCHAR(150)  CHECK(LENGTH(NAME) >= 10)")//queremos que la columna no sea anulable . Si usamos name="nombre" se le asigna este nombre a esta columna name. en la tabla
	@Size(min=10,max=150, message="{model.data.validation.CustomerContact.name}") //Name must be from 10 to 150 characters
	private String name;
	
	@Column(columnDefinition="CHAR(18) CHECK(LENGTH(PHONE) >= 9)")
	@Size(min=9 ,max=20, message="{model.data.validation.CustomerContact.phone}") //Phone must from 9 to 20 characters
	@NotNull
	private String phone; 
	
	
	@Email
	@Size(min=3, max=255,message="{model.data.validation.CustomerContact.email}")//email must have from 0 to 255 characters
	private String email;
	
	
	@Size(min=0, max=255,message="{model.data.validation.CustomerContact.observations}")
	private String observations;
	//to string to exclud lo que hace es excluir este campo y no lo va a crear?. es para evitar el bucle infinito
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name= "idCustomer", referencedColumnName= "id")
    private Customer customer;
	
	
}
