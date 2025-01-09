package com.core.timmy.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//interface funcional se designar con un @  son anotación

/*las anotaciones se pueden a nivel de clase, a nivel de métodos o a nivel de línea de código*/
@Target(ElementType.METHOD) // Where annotation is applicable in source code. Va a afectar a nivel de método porque le pusimos METHOD. (Ubicación)
@Retention(RetentionPolicy.RUNTIME) //Where annotation is available (in this case to JVM runtime). Esto es cuando va a ser activa o aplicable. Runtime = cuando se este ejecutando el programa.
public @interface LogExecutionTimeAnnotation {
	

	
	
}
