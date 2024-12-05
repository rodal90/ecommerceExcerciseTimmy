package com.core.timmy.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

//@service, @respository y @controller vienen derivan de component como anotaciones. El Spring Bean es hecho con @Componenet.- "JavaBean(Normal)+Singleton = SpringBean. 
//Que es un singleton

@Component
@Aspect   //hace que pueda ser gestionada como programación orientada a objetos
@Slf4j
public class Monitor {
	
	@Before("execution(* com.core.timmy.*.*.save*(..))")//los dos puntos significa que no importa la cantidad de parametros o si los metodos save están sobrecargados.  
	//el primer asterisco retorna cualquier cosa incluso void. 
	private void aspectBeforeSave(JoinPoint joinPoint) {
		log.info("Monitor aspectBeforeSave > "+ joinPoint.getSignature().toString());
	}
	
	// With exception or not it shows up.
	@After("execution(* com.core.timmy.*.*.save*(..))")//los dos puntos significa que no importa la cantidad de parametros o si los metodos save están sobrecargados.  
	//el primer asterisco retorna cualquier cosa incluso void. Este se ejecuta siempre incluso si hay una incidencia con la operacion que estamos monitorizando.
	private void aspectAfterSave(JoinPoint joinPoint) {
		log.info("Monitor aspectAfterSave > "+ joinPoint.getSignature().toString() + " >> " + joinPoint.getSignature().getName());
	}
	
	//Only if no exception happens it shows up.
	@AfterReturning(pointcut= "execution(* com.core.timmy.*.*.save*(..))", returning= "entitySaved")//los dos puntos significa que no importa la cantidad de parametros o si los metodos save están sobrecargados.  
	//After returning solo se ejecuta si no se genero ningun problema o incidencia. y se puedo completar la operación que estamos monitarizando.
	//el primer asterisco retorna cualquier cosa incluso void.   
	//Si ponemos mas de un parametro hay que dejar de forma explicita explicado que es el pointcut, asi se diferencia del returning. 
	private void aspectAfterReturningSave(JoinPoint joinPoint, Object entitySaved) { //usamos object porque en tiempo de ejecución no sabemos que clase es la entity entonces, vamos a la segura.
		//porque savemos que todos vienen del padre que es object.
		log.info("Monitor aspectAfterReturningSave > "+ joinPoint.getSignature().toString() + " >> " + joinPoint.getSignature().getName()+ " >>> " + ((entitySaved ==null) ? "entitySaved is Null" : entitySaved.toString()));
	}
	
	@Around("execution(* com.core.timmy.*.*.save*(..))")//los dos puntos significa que no importa la cantidad de parametros o si los metodos save están sobrecargados.  
	//el primer asterisco retorna cualquier cosa incluso void. Este se ejecuta siempre incluso si hay una incidencia con la operacion que estamos monitorizando.
	private Object aspectAroundSave(ProceedingJoinPoint joinPoint) throws Throwable { //lo de claramos como object porquen o sabemos de que clase es.
		log.info("Monitor ANTES!! aspectAroundSave > "+ joinPoint.getSignature().toString() + " >> " + joinPoint.getSignature().getName());
		
		//hay una clase que hereda de joinpoint que se llama proceeding joinPoint, y hay que importarla
		
		Object proceed = joinPoint.proceed();
		
		
		log.info("Monitor DESPUES!! aspectAroundSave > "+ joinPoint.getSignature().toString() + " >> " + joinPoint.getSignature().getName());
		
		return proceed;
	}

}
