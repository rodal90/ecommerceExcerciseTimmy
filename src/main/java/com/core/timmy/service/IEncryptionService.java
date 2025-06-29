package com.core.timmy.service;
//tampoco tiene repositorio ni modelo

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.core.timmy.data.model.Encryption;


import java.util.List;

@Service
public interface IEncryptionService{
	//interface de servicio
	
	public Encryption newEntity();
	//añadimos un segundo método que realice la encriptación

	public Encryption encryptText(Encryption encryption);
	public List<String> findAllEncodersName();
	
	public List<PasswordEncoder>getEncoderList();

}
