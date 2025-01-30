package com.core.timmy.config;

import org.springframework.context.annotation.Configuration;


import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;

//esto es para poder agregar nuevo archivo de properties, le damos el path en este caso es para ir al archivo secretkey.properties

@Configuration
@EncryptablePropertySource( name="EncryptedProperties",
		value= {"classpath:properties/encrypted.properties"})
public class ConfigForEncryptedProperties{
	

}
