package com.core.timmy.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.core.timmy.data.model.Encryption;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

public interface IEncryptionController {
//principal usuario autentica, modelo contiene datos que se inyectan, 
	 public String encryptionGet(Principal principal, Model model, HttpServletRequest request);

	//principal usuario autentica, modelo contiene datos que se inyectan, el post tiene dos mas porque recupera los datos del formulario,
	 //una de ellas es la clase que creamos, bindingResult nos ayuda a validar las limitantes que ponemos los modelos para que se comprueben el tamaño máximo. esta en Encryption.java.
	 
	 //arroba valid es una anotación, significa que esta validando que todo lo que tenga el objeto , le dice a java en tiempo de ejecución cuando el tipo le de al sumbit cuando se recuperen los datos, validame que
	 //todo lo que tenia que cumplirse el, model model ,  datos y parametros
	public String encryptionPost(@Valid Encryption encryption, BindingResult bindingResult, Principal principal, Model model,
			HttpServletRequest request);

	
	

}
