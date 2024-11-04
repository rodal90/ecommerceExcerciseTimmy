package com.core.timmy.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/*en todos hay que importar algo para definirlos, con sus etiquetas ej: @Controller, @Service, @*/

@Controller
public interface IContactController {
	

	public String contactListGet(Principal principal, Model model);
	
	

}
