package com.core.timmy.controllerImpl;

import java.security.Principal;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.core.timmy.controller.ICustomerController;
import com.core.timmy.controller.IProviderController;
import com.core.timmy.controller.IStartController;
import com.core.timmy.data.model.Customer;
import com.core.timmy.data.model.Provider;
import com.core.timmy.service.ICustomerService;
import com.core.timmy.service.IProviderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProviderControllerImpl implements IProviderController {
	@Autowired

	private IProviderService providerService;
	
	/*
	 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace para
	 * que se conecte con este método*/


	@Override
	@GetMapping({ "/providerListGet" }) 
	public String providerListGet(Principal principal, Model model) {
		log.info("TRAZA providerListGet");
		
		model.addAttribute("username", principal.getName());
		model.addAttribute("userPicture", "");

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		model.addAttribute("providerList", this.providerService.findAll()
		    .stream()
		    .sorted(Comparator.comparing(Provider::getName))
		    .collect(Collectors.toList())
		    );
		
		//model.addAttribute("providerList", this.providerService.findAllByOrderByName());

		return "providerList";
	}

	
}
