package com.core.timmy.data.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*Esto a través de entity se convierte en una clase especial que va a funcionar como espejo, va tener que tener 
una contra parte en una base de datos.*/

@Entity
@Table(name = "ROLES") //Preferible usar librerias más cercanas a java como jakarta


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // Nos crea el constructor vacio
@ToString
/* El AllArgsConstructor no agregar los atributos final o static. */

/*
 * con lombok se puede crear código fuente a través de comentar con @ y podemos
 * usarlo para generar Getters y Setters, cuando borrramos un atributo lombock
 * automáticamente borra los getters o setters o los modifica se por alguna
 * razón es necesario modificar el nombre
 */

public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String rolename;
	
	/*@ManyToMany
    private Set<User> userSet; /*con esto como es una colección de objetos, tendre acceso a una colección completa de todos
	los atributos de User, eso sería lo que colectaría*/


}
