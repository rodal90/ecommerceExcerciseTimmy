package com.core.timmy.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.core.timmy.data.model.Encryption;
import com.core.timmy.service.IEncryptionService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class EncryptionServiceImpl implements IEncryptionService {
	
	@Autowired
	private List<PasswordEncoder> encoderList= new ArrayList<PasswordEncoder>();
	
	
	@Override
	public Encryption newEntity() {
		
		return new Encryption();
	}

	@Override
	public Encryption encryptText(Encryption encryption) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> findAllEncodersName() {
		
		log.info("Beans - names of PaswordEncoder class registered in Spring Container: ");
	
		return this.encoderList
				.stream()
				/*.peek(x -> log.info("\t> " + x.toString()))*/
				.peek(x-> log.info("\t\t>> " + x.getClass().getSimpleName().replace("PasswordEncoder","")))
				.map(x -> x.toString())
				.collect(Collectors.toList());
	}
	

}
