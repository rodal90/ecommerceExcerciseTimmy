package com.core.timmy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//esto es para poder agregar nuevo archivo de properties, le damos el path en este caso es para ir al archivo secretkey.properties

@Configuration
@PropertySource({"classpath:properties/secretkey.properties"})
public class ConfigForJasyptStarter{
	

}
