package com.core.timmy.data.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
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

	@Override
	public int hashCode() {
		return Objects.hash(email, enabled, expiryDateAccount, expiryDateCredentials, fullname, lockedAccount, password,
				roleSet, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(enabled, other.enabled)
				&& Objects.equals(expiryDateAccount, other.expiryDateAccount)
				&& Objects.equals(expiryDateCredentials, other.expiryDateCredentials)
				&& Objects.equals(fullname, other.fullname) && Objects.equals(lockedAccount, other.lockedAccount)
				&& Objects.equals(password, other.password) && Objects.equals(roleSet, other.roleSet)
				&& Objects.equals(username, other.username);
	}

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
	/*es bueno guardar en colecciones las relaciones o vinculaciones entre tablas de bases de datos que nos interesen
	, en este caso entre user.java y Role.java*/
	
	@ManyToMany
	@JoinTable(name= "USERS_HAS_ROLES") //Forma de modificar el nombre de la tercera tabla
    private Set<Role> roleSet;
	
	
	
}
