package com.core.timmy.data.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*Esto a través de entity se convierte en una clase especial que va a funcionar como espejo, va tener que tener 
una contra parte en una base de datos.*/
import lombok.extern.slf4j.Slf4j;

@Entity
//@Table(name = "COMUNICATION") //Preferible usar librerias más cercanas a java como jakarta


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // Nos crea el constructor vacio
@ToString
@Slf4j

//@ManyToOne lo que haces es generarnos los join a travez del JPA

//comunicación es la tabla que está conectada con todo lo demás y luego al rededor tenes la otras tablas, como budget, status y customer contac
public class Comunication implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER) //lo usamos para seleccionar que tipo de fetch queremos que haga
	@JoinColumn(name="idBudget", referencedColumnName="id")
	private Budget budget;
	
	
	@ManyToOne
	@JoinColumn(name="idStatus", referencedColumnName="id")
	
	private Status status;
	
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name="idCustomerContact", referencedColumnName="id")
	
	private CustomerContact customerContact;
	
	
	private String message;
	private LocalDate messageDate;
	private Double amount;
	
	
}
