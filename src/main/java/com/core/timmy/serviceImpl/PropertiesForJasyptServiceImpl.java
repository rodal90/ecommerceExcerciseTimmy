package com.core.timmy.serviceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;


import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/*
 * Service to test Jasypt encryption properties.
 * If in your file 'encrypted.properties' you put 
 * i.e. encrypted.property=ENC(nrmZtkF7T0kjG/VodDvBw93Ct8EgjCA+)
 * now when you do environment.getProperty("encrypted.property") 
 * or use @Value("${encrypted.property}") what you get 
 * is the decrypted version of secret.property
 */

@Service
@Getter
@Slf4j
public class PropertiesForJasyptServiceImpl {

    private static final String ENCRYPTED_VALUE_PREFIX = "ENC(";
    private static final String ENCRYPTED_VALUE_SUFFIX = ")";

    @Value("${jasypt.encryptor.password}")
    private String secretkey;
    
    @Value("${admin.email}")
    private String adminEmail;
    
    @Autowired
    private DefaultLazyEncryptor encryptor;
    
    @Autowired
    private Environment environment;
    
    private Map<String, String> encryptedPropertiesMap = null;
    
    public String getPropertyUsingEnvironment(String propertyName) {
        return (this.environment.getProperty(propertyName) == null)
        		? "PROPERTY NOT FOUND"
        		: this.environment.getProperty(propertyName);
    }
    
    public Map<String, String> getEncryptedPropertiesMap() {
    	if (this.encryptedPropertiesMap == null) {
    		this.encryptedPropertiesMap = new HashMap<>();
    		this.loadMapFromEncryptedProperties();
    	}
    	return this.encryptedPropertiesMap;
    }
    
    private void loadMapFromEncryptedProperties() {    	
    	try {
    		// Leemos el fichero .properties en un objeto de la clase Properties
			Properties encryptedProperties = PropertiesLoaderUtils.loadAllProperties("properties/encrypted.properties");
			log.info("encrypted.properties contiene " + encryptedProperties.size() + " propiedades");
			log.info("secretkey contiene " + secretkey);
			
			encryptedProperties.forEach((key, value) -> { 
				if(!this.isEncryptedValue(value.toString())) {
					log.info("NO ENCRIPTADO\t> " + key + "\t>> " + value);
					encryptedPropertiesMap.put(key.toString(), value.toString());
				}
				else {
					log.info("SI ENCRIPTADO\t> " + key + "\t>> " + value + "\t>>> " + 
							encryptor.decrypt(value.toString().substring(
									ENCRYPTED_VALUE_PREFIX.length(),
									(value.toString().length() - ENCRYPTED_VALUE_SUFFIX.length())))
						);
					encryptedPropertiesMap.put(key.toString(), encryptor.decrypt(value.toString().substring(
							ENCRYPTED_VALUE_PREFIX.length(),
							(value.toString().length() - ENCRYPTED_VALUE_SUFFIX.length())))							
						);			
				}
			});
			//log.info("ERROR??: " + encryptor.decrypt("En un lugar de la Mancha"));
		} catch (IOException e) {
			//e.printStackTrace();
			log.info("EXCEPTION: .properties not accesible: " + e.getMessage());
		} catch (EncryptionOperationNotPossibleException ex) {
			//e.printStackTrace();
			log.info("EXCEPTION: .property should not be decrypted: " + ex.getMessage());			
		}
    	this.showEncryptedPropertiesMap();
    }
    
    public void showEncryptedPropertiesMap() {
    	log.info("showEncryptedPropertiesMap contenido de EncryptedPropertiesMap: ");
    	this.encryptedPropertiesMap.forEach((k,v) -> log.info("\tKey: " + k + "\t> Value: " + v));
    }
    
    private boolean isEncryptedValue(String value) {
    	if (value == null) {
    		return false;
    	}
    	String trimmedValue = value.trim();
    	return (trimmedValue.startsWith(ENCRYPTED_VALUE_PREFIX)
    		&& trimmedValue.endsWith(ENCRYPTED_VALUE_SUFFIX));
    }
    
}