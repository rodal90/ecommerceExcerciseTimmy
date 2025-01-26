package com.core.timmy.data.model;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//Login es modelo pero no neceista repository ni entity porque no la vamos a usar contra una base de datos.


@Getter
@Setter
@NoArgsConstructor // Nos crea el constructor vacio
@ToString

public class Login {
	//Etiqueta de validación para controlar la cantidad de caractéres máximos y mínimos
	@Size(min=3, max=50, message="username must have from 3 to 50 characters")
	// Reg Exp: Expresiones regulares.
	@Pattern(regexp = "[A-Za-z0-9_-]{3,50}", message = "username must have only letters, numbers,_and -")
	private String username;
	
	//Es importante tener en cuenta que la contraseña se va a cifrar.
	
	@Size(min=5, max=255, message="password must have from 5 to 255 characters")
	private String password;
	
}
