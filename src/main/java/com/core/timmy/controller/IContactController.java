package com.core.timmy.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.core.timmy.data.model.Contact;
import com.core.timmy.data.model.Provider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/*en todos hay que importar algo para definirlos, con sus etiquetas ej: @Controller, @Service, @*/

@Controller
public interface IContactController {
	

	public String contactListGet(Principal principal, Model model,HttpServletRequest request);

   public String contactViewGet(Long id, Principal principal, Model model, HttpServletRequest request);

   public String contactDeleteGet(Long id, Principal principal, Model model, HttpServletRequest request);

    public String contactDeleteConfirmed(Long id, Principal principal, Model model, HttpServletRequest request);

	public String contactUpdateGet(Long id, Principal principal, Model model, HttpServletRequest request);


	public String contactUpdatePost(@Valid Contact contact, BindingResult bindingResult,
			Principal principal, Model model, HttpServletRequest request);

	public String contactAddGet(Principal principal, Model model, HttpServletRequest request) throws Exception;
	
	

}
