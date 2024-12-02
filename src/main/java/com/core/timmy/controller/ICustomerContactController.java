package com.core.timmy.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

/*en todos hay que importar algo para definirlos, con sus etiquetas ej: @Controller, @Service, @*/

@Controller
public interface ICustomerContactController {
	

	public String customerContactListGet(Principal principal, Model model,HttpServletRequest request);

   public String customerContactViewGet(Long id, Principal principal, Model model, HttpServletRequest request);
	
	

}
