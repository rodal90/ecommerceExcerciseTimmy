package com.core.timmy.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.core.timmy.data.model.Customer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/*en todos hay que importar algo para definirlos, con sus etiquetas ej: @Controller, @Service, @*/

@Controller
public interface ICustomerController {
	
	/*añadir public a las variables porque es obligatorio, sino no se va poder acceder a el desde los otros archivos*/
	
	public String customerListGet(Principal principal, Model model);

	 public String customerViewGet(Long id, Principal principal, Model model, HttpServletRequest request);

	 public String customerUpdateGet(Long id, Principal principal, Model model, HttpServletRequest request);

	public String customerUpdatePost(@Valid Customer customer, BindingResult bindingresult, Principal principal, Model model,
			HttpServletRequest request);
	
	

}
