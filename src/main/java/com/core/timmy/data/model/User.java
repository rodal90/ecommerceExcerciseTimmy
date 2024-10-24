package com.core.timmy.data.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
import lombok.extern.slf4j.Slf4j;
/*Esto a través de entity se convierte en una clase especial que va a funcionar como espejo, va tener que tener 
una contra parte en una base de datos.*/

@Entity
@Table(name = "USERS") //Preferible usar librerias más cercanas a java como jakarta


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // Nos crea el constructor vacio
@ToString
@Slf4j
//es  una libreria estandar de log. No es muy compleja.
/* El AllArgsConstructor no agregar los atributos final o static. */

/*
 * con lombok se puede crear código fuente a través de comentar con @ y podemos
 * usarlo para generar Getters y Setters, cuando borrramos un atributo lombock
 * automáticamente borra los getters o setters o los modifica se por alguna
 * razón es necesario modificar el nombre
 */

public class User implements Serializable, UserDetails {

	@Override
	public int hashCode() {
		//
		return Objects.hash(email, enabled, expiryDateAccount, expiryDateCredentials, fullname, lockedAccount, password,
				roleSet, username);
	}

	@Override
	public boolean equals(Object obj) {
		//son los punteros los que se comparan y se ve si apuntan al mismo objeto
		if (this == obj)
			return true;
		//aca se compara un puntero y se verifica si uno esta vacia, si el puntero no tiene objeto, es null.
		if (obj == null)
			return false;
		//aca se compara si la clase del objeto es la misma si no es la misma retorna falso.
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		//aca se compara si aunque sean dos objetos distintos, verifica si tienen el mismo user name. 
		return Objects.equals(username, other.username);
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Declara new list of granted authorites
		List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
		
		// obtener los roles del usuario autenticados
		this.getRoleSet().stream().map(x -> x.getRolename()).forEach(x->simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(x)));
		
		//Show granted authorities
		log.info("Roles from "+ this.getUsername()+ ": "+ 
		
				simpleGrantedAuthorityList.stream()
		.map(x -> x.getAuthority()).collect(Collectors.joining("|", "{", "}"))
				
				);
		
		
		return simpleGrantedAuthorityList;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		//return UserDetails.super.isAccountNonExpired();
		return this.getExpiryDateAccount().isAfter(LocalDate.now());
	}

	@Override
	public boolean isAccountNonLocked() {
		
		//return UserDetails.super.isAccountNonLocked();
		return !this.getLockedAccount();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		//return UserDetails.super.isCredentialsNonExpired();
		return this.getExpiryDateCredentials().isAfter(LocalDate.now());
		}

	@Override
	public boolean isEnabled() {
		
		//return UserDetails.super.isEnabled();
		
		return this.getEnabled();
	}
	
	
	
}
