package com.core.timmy.data.model;

import java.io.Serializable;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*Esto a través de entity se convierte en una clase especial que va a funcionar como espejo, va tener que tener 
una contra parte en una base de datos.*/
import lombok.extern.slf4j.Slf4j;

@Entity
//@Table(name = "BUDGET") //Preferible usar librerias más cercanas a java como jakarta


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // Nos crea el constructor vacio
@ToString
@Slf4j
/* El AllArgsConstructor no agregar los atributos final o static. */

/*
 * con lombok se puede crear código fuente a través de comentar con @ y podemos
 * usarlo para generar Getters y Setters, cuando borrramos un atributo lombock
 * automáticamente borra los getters o setters o los modifica se por alguna
 * razón es necesario modificar el nombre
 */

public class Budget implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Positive
	private Long id;
	
	
	@NotNull
	@DecimalMax(value="999999999.99")//decimal máx y decimal min acepta valores númericos y strings con valores númerico por eso va entre comillas
	@DecimalMin(value="0.00")
	@Column(nullable=false, columnDefinition="DECIMAL(10,2) DEFAULT 0 CHECK(CURRENTAMOUNT >= 0.0)")
	private Double currentAmount;
	
	
	
	@ManyToOne
	@JoinColumn(name="idCurrentStatus", referencedColumnName="id")
	private Status currentstatus;
	
	
	@ToString.Exclude
	@OneToMany(mappedBy ="budget") //la comunicación desde budget hacia las comunicaciones
    private List<Comunication> comunicationList;
	
	
	
	
	/*@ManyToMany
    private Set<User> userSet; /*con esto como es una colección de objetos, tendre acceso a una colección completa de todos
	los atributos de User, eso sería lo que colectaría*/


}
