package com.core.timmy.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.core.timmy.data.model.Encryption;
import com.core.timmy.service.IEncryptionService;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Service
@Getter
@Slf4j
public class EncryptionServiceImpl implements IEncryptionService { //uso de lombock para tener getter
	
	@Autowired
	private List<PasswordEncoder> encoderList= new ArrayList<PasswordEncoder>();
	
	
	@Override
	public Encryption newEntity() {
		
		return new Encryption();
	}

	@Override
	public Encryption encryptText(Encryption encryption) {
		// Get textoToEncrypt and algorithm´s name
		// Obtain the passwordEncoder related to the algorithm´s name
		Optional<PasswordEncoder>passwordEncoder = this.encoderList
		.stream()
		.filter(x->x.getClass().getSimpleName().replace("PasswordEncoder","").equals(encryption.getPasswordEncoderString())) //en objetos se usa equals SIEMPRE.
		.findFirst();
		
		if(passwordEncoder.isEmpty()){
			encryption.setTextEncrypted("Error - NO ENCRYPTION ALGORITHM");
			
		}
		else {
			encryption.setTextEncrypted(passwordEncoder.get().encode(encryption.getTextToEncrypt()));//saco el encoder con el get... el encode para encryptar y el matches para comparar y verificar que 
		}
		
		//Calculate and return the Encryption object with textEncrypted filled. 
		return encryption;
	}

	@Override
	public List<String> findAllEncodersName() {
		
		log.info("Beans - names of PaswordEncoder class registered in Spring Container: ");
	
		return this.encoderList
				.stream()
				/*.peek(x -> log.info("\t> " + x.toString()))*/
				.peek(x-> log.info("\t\t>> " + x.getClass().getSimpleName().replace("PasswordEncoder","")))
				.map(x -> x.getClass().getSimpleName().replace("PasswordEncoder",""))
				.collect(Collectors.toList());
	}
	

}
