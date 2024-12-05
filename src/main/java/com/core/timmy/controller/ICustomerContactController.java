package com.core.timmy.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.core.timmy.data.model.CustomerContact;
import com.core.timmy.data.model.Provider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/*en todos hay que importar algo para definirlos, con sus etiquetas ej: @Controller, @Service, @*/

@Controller
public interface ICustomerContactController {
	

	public String customerContactListGet(Principal principal, Model model,HttpServletRequest request);

   public String customerContactViewGet(Long id, Principal principal, Model model, HttpServletRequest request);

   public String customerContactDeleteGet(Long id, Principal principal, Model model, HttpServletRequest request);

    public String customerContactDeleteConfirmed(Long id, Principal principal, Model model, HttpServletRequest request);

	public String customerContactUpdateGet(Long id, Principal principal, Model model, HttpServletRequest request);


	public String customerContactUpdatePost(@Valid CustomerContact customerContact, BindingResult bindingResult,
			Principal principal, Model model, HttpServletRequest request);
	
	

}
