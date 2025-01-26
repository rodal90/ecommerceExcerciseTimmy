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
/*UserDetails es una interfaz que representa los detalles de un usuario en el sistema de seguridad de Spring. En otras palabras,
 *  esta interfaz es la que se usa para obtener información relacionada con un usuario que está autenticado, 
 *  como su nombre de usuario, contraseña, roles, etc.*/
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
@Table(name = "USERS") // Preferible usar librerias más cercanas a java como jakarta

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
		/*
		 * esta utilizando los atributos de la clase para generar el hash code de esa
		 * forma, cuando se utilice el equals va comparar la lógica de los datos
		 * utilizados para asegurarse de que son iguales
		 */
		return Objects.hash(email, enabled, expiryDateAccount, expiryDateCredentials, fullname, lockedAccount, password,
				roleSet, username);
	}

	@Override
	public boolean equals(Object obj) {
		// son los punteros los que se comparan y se ve si apuntan al mismo objeto
		if (this == obj)
			return true;
		// aca se compara un puntero y se verifica si uno esta vacia, si el puntero no
		// tiene objeto, es null.
		if (obj == null)
			return false;
		// aca se compara si la clase del objeto es la misma si no es la misma retorna
		// falso.
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		// aca se compara si aunque sean dos objetos distintos, verifica si tienen el
		// mismo user name.
		return Objects.equals(username, other.username);
	}

	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	private String email;
	private String password;// asegurarse del algoritmo de encriptación para saber cuantos caractéres hay
							// que guardar.No-60
	private String fullname;

	private LocalDate expiryDateAccount; /* Fecha de expiración de la password */
	private LocalDate expiryDateCredentials; /* Fecha de expiración de los privilegios */
	private Boolean enabled;
	private Boolean lockedAccount; /*
									 * en bases de datos relacionales, SQL los boolean pueden ser INT(1) o
									 * TINYINT(1)
									 */
	/*
	 * es bueno guardar en colecciones las relaciones o vinculaciones entre tablas
	 * de bases de datos que nos interesen , en este caso entre user.java y
	 * Role.java
	 */

	@ManyToMany
	@JoinTable(name = "USERS_HAS_ROLES") // Forma de modificar el nombre de la tercera tabla
	private Set<Role> roleSet;

	/*
	 * GrantedAuthority es una interfaz en Spring Security que representa un rol o
	 * permiso que se le concede a un usuario. Cuando un usuario inicia sesión en
	 * una aplicación, se le asignan ciertos roles o permisos, y GrantedAuthority es
	 * la forma estándar de representar esos roles o permisos.
	 */
	
	/*Esta parte es un wildcard (comodín) que indica que la colección puede contener
	 *  cualquier tipo de objeto que extienda (o implemente) la interfaz GrantedAuthority.*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Cada rol o permiso se convierte en un objeto que implementa esta interfaz.
		// Declara new list of granted authorites
		/*
		 * Generalmente, esta interfaz tiene un único método: getAuthority(), que
		 * devuelve el nombre del rol o permiso (por ejemplo, "ROLE_ADMIN" o
		 * "ROLE_USER").
		 */
		/*
		 * La clase SimpleGrantedAuthority toma una cadena de texto (generalmente un
		 * nombre de rol) y lo convierte en un objeto de tipo GrantedAuthority.
		 */
		List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();

		// obtener los roles del usuario autenticados
		/*Explicación: "this" hace referencia al objeto user actual y con el método get del Set RoleSet
		 * lo llamamos y lo convertimos en un stream al cual le hacemos un map para ir sacando solo el nombre
		 * de cada rol, y luego el ".forEach" lo usamos para recorrer cada Rolename y cada uno de ellos 
		 * lo vamos agregando al la Lista de Arrays SimpleGranteAuthorityList, solo recibe objetos de tipo
		 * SimpleGrantedAuthority entonces le pasamos como parámetro cada rolename "x" para que los convierta
		 * en objetos SimpleGrantedAuthority*/
		this.getRoleSet().stream().map(x -> x.getRolename())
				.forEach(x -> simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(x)));

		// Show granted authorities
		log.info("Roles from " + this.getUsername() + ": " +

				simpleGrantedAuthorityList.stream().map(x -> x.getAuthority())
						.collect(Collectors.joining("|", "{", "}"))

		);

		return simpleGrantedAuthorityList;
	}

	@Override
	public boolean isAccountNonExpired() {

		// return UserDetails.super.isAccountNonExpired();
		return this.getExpiryDateAccount().isAfter(LocalDate.now());
	}

	@Override
	public boolean isAccountNonLocked() {

		// return UserDetails.super.isAccountNonLocked();
		return !this.getLockedAccount();
	}

	@Override
	public boolean isCredentialsNonExpired() {

		// return UserDetails.super.isCredentialsNonExpired();
		return this.getExpiryDateCredentials().isAfter(LocalDate.now());
	}

	@Override
	public boolean isEnabled() {

		// return UserDetails.super.isEnabled();

		return this.getEnabled();
	}

}
