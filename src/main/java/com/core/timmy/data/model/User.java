package com.core.timmy.data.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*Esto a través de entity se convierte en una clase especial que va a funcionar como espejo, va tener que tener 
una contra parte en una base de datos.*/

@Entity
@Table(name = "USERS") //Preferible usar librerias más cercanas a java como jakarta


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

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String username;
	
	private String email;
	private String password;//asegurarse del algoritmo de encriptación para saber cuantos caractéres hay que guardar.No-60
	private String fullname;
	
	private LocalDate expiryDateAccount; /* Fecha de expiración de la password */
	private LocalDate expiryDateCredentials; /* Fecha de expiración de los privilegios */
	private Boolean enabled;
	private Boolean lockedAccount; /*
								 * en bases de datos relacionales, SQL los boolean pueden ser INT(1) o
								 * TINYINT(1)
								 */

}
