package com.core.timmy.data.validation;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = NifValidator.class )//donde esta la clase que me va servir el método para validar
@Documented                             //a anotación @Documented en lang.annotation (y en Java en general) forma parte del paquete java.lang.annotation y
//sirve para indicar que una anotación personalizada debe incluirse en la documentación generada por herramientas como Javadoc.
@Retention(RUNTIME) //en que momento se ejecuta la anotación que creamos
@Target({FIELD,METHOD})
public @interface INifConstraint {   //le ponemos el arroba para que sepa que va servir para crear etiquetas, requiere la declaración de dos métodos

	String message() default "Nif inválido. Formato de 8 dígitos y 1 letra: 12345678A";
	
	//las siguientes dos declaraciones son requeridas incluso si la anotación no tiene parametros:
	
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};	
	
	//Sirven para agrupar anotaciones que utilizemos en conjunto , y el otro para  Cuando defines una anotación para validación, como @NotNull o @Size,
	//estas permiten extender funcionalidad a través de metadatos. El elemento payload() se usa como un canal para proporcionar información adicional 
	//que pueda ser útil para el procesamiento personalizado de validaciones.
	
	
	
	

}
