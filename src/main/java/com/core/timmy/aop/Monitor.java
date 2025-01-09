package com.core.timmy.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
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
	/*
	 * Advice methods defined in the same @Aspect class that need to run at the same join point are assigned precedence based on their advice type in the following order,
	 * from highest to lowest precedence: @Around (before), @Before, @AfterThrowing, @AfterReturning, @After, @Around (after). Note, however, that an @After advice method will effectively
	 * be invoked after any @AfterReturning or @AfterThrowing advice methods in the same aspect, following AspectJ's "after finally advice" semantics for @After. 
	 * */
	
	@Before("execution(* com.core.timmy.*.*.save*(..))")//los dos puntos significa que no importa la cantidad de parametros o si los metodos save están sobrecargados.  
	//el primer asterisco retorna cualquier cosa incluso void. 
	private void aspectBeforeSave(JoinPoint joinPoint) {
		log.info("Monitor aspectBeforeSave > "+ joinPoint.getSignature().toString() //nos permite averiguar cuantos argumentos y que argumentos tiene el metodo al que va afectar esto eso hace ARGs
				+ " >> joinPoint.getArgs().length= " + joinPoint.getArgs().length);
		log.info("Monitor aspectBeforeSave params > " + " >> entity to be saved= " + ((joinPoint.getArgs() !=null)? joinPoint.getArgs()[0] : "IS NULL!!!"));
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
	
	//combinación de before if after
	@Around("execution(* com.core.timmy.*.*.save*(..))")//los dos puntos significa que no importa la cantidad de parametros o si los metodos save están sobrecargados.  
	//el primer asterisco retorna cualquier cosa incluso void. Este se ejecuta siempre incluso si hay una incidencia con la operacion que estamos monitorizando.
	private Object aspectAroundSave(ProceedingJoinPoint joinPoint) throws Throwable { //lo de claramos como object porquen o sabemos de que clase es.
		log.info("Monitor ANTES!! aspectAroundSave > "+ joinPoint.getSignature().toString() + " >> " + joinPoint.getSignature().getName());
		
		//hay una clase que hereda de joinpoint que se llama proceeding joinPoint, y hay que importarla
		
		Object proceed = joinPoint.proceed();
		
		
		log.info("Monitor DESPUES!! aspectAroundSave > "+ joinPoint.getSignature().toString() + " >> " + joinPoint.getSignature().getName());
		
		return proceed;
		
		
		
	}
	
	//@Around("execution(* com.core.timmy.serviceImpl.ContactServiceImpl.findAll(..))")//los dos puntos significa que no importa la cantidad de parametros o si los metodos save están sobrecargados.  
	//el primer asterisco retorna cualquier cosa incluso void. Este se ejecuta siempre incluso si hay una incidencia con la operacion que estamos monitorizando.
	@Around("@annotation(LogExecutionTimeAnnotation)") //cualquier método de cualquier clase que tenga esta anotación
	private Object aspectAroundLogExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable { //lo de claramos como object porquen o sabemos de que clase es.
		
		long startTime= System.currentTimeMillis();
		
		Object proceed = joinPoint.proceed();
		
		long executionTime= System.currentTimeMillis() - startTime;
		
		log.info("Time elapsed: " + executionTime + "ms > " + joinPoint.getSignature().toString() + " >> " + joinPoint.getSignature().getName());
		
		return proceed;
		
		
		
	}
	
	// Only if exception is throwing. Solo se ejecute si el método que esta afectado "save" no trata la excepción, si hace la gestión de la excepción no se dispara. Si hace un throw si salta,
   //pero si caputra entonces no.  ex = exception 
		@AfterThrowing(pointcut = "execution(* com.core.timmy.data.repository*.*.save*(..))", throwing = "ex")
		private void aspectAfterThrowingAddGet(JoinPoint joinPoint, Exception ex) {
			
			log.info("Monitor aspectAfterThrowingAddGet > " + joinPoint.getSignature().toString()
					+ " >> " + joinPoint.getSignature().getName()
					+ " >>> Throws Exception: " + ex.getMessage());
		}

}
