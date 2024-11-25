package com.core.timmy.data.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NifValidator implements ConstraintValidator<INifConstraint, String> { //pide con que interface se asocia, en este caso el INifConstraint, de que tipo es el campo que vamos a validar

	@Override
	public boolean isValid(String nif, ConstraintValidatorContext context) {// Este es el que dice si el contenido del nif es correcto o incorrecto, Aqui tambien ponemos que tipo de dato vamos a validar
	//le podemos llamar nif al valor para que nos quede má claro.
		//Lo que quiero validar:
		// No puede ser nulo. Not Null.
		//-Que tenga el formato correcto, ejemplo: 12345678A
		// Calcula la letra correcta
		return nif !=null                            
				&& nif.matches("[0-9]{1,8}[A-Za-z]{1}")
				&& this.letterOfNifIsCorrect(nif);
				//los strings tiene un metodo que se llama matches para comparar y ver si son verdaderos. 
		
	}

public static boolean letterOfNifIsCorrect(String nif) {
	
	String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
	
	String nifLetter= nif.substring(nif.length() -1); //dame el carácter de una posición, calcula la longitud de chart y con lenght() cuenta la cantidad de posiciones y restales uno para dar con la ultima letra
	
	int numbersOfNif= Integer.parseInt(nif.substring(0,nif.length()-1));//sirve para trozear el string y lo que le pasamos son los delimitadores el le resta uno y yo le resto otro.
	
	
	String rightLetter=letters
			.substring(numbersOfNif % letters.length(), (numbersOfNif % letters.length()) + 1);
	
	
	log.info("nif= "+ nif 
			+ "> nifLetter: " + nifLetter 
			+ ">> numbersOfNif: " + numbersOfNif 
			+" >>> rightLetter: " + rightLetter 
			+">>>> Validacion= " + nifLetter.equalsIgnoreCase(rightLetter));
	
	

	return nifLetter.equalsIgnoreCase(rightLetter); //asi es como no toma en cuenta si es mayúscula o minúscula y puede comparar las letras
	
}

public static boolean nifIsCorrectAndNotNull(String Nif) {
	
	return Nif != null
			&& Nif.matches("[0-9]{1,8}[A-Za-z]{1}")
			&& letterOfNifIsCorrect(Nif);
};

}
