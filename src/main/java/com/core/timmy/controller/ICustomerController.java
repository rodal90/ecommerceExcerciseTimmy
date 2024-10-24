package com.core.timmy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/*en todos hay que importar algo para definirlos, con sus etiquetas ej: @Controller, @Service, @*/

@Controller
public interface ICustomerController {
	
	public String customerListGet(Model model);
	
	

}
