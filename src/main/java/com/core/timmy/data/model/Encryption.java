package com.core.timmy.data.model;


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

public class Encryption {
	//Etiqueta de validación para controlar la cantidad de caractéres máximos y mínimos
	@Size(min=1, max=50, message="textToEncrypt must have from 1 to 50 characters")
	private String textToEncrypt;
	
	private String passwordEncoderString;
	//Es importante tener en cuenta que la contraseña se va a cifrar.
	
	@Size(min=1, max=255, message="textEncrypted must have from 1 to 255 characters")
	private String textEncrypted;
	
}
