package com.core.timmy.controllerImpl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.core.timmy.controller.ICustomerController;
import com.core.timmy.controller.IStartController;
import com.core.timmy.service.ICustomerService;

@Controller
public class CustomerControllerImpl implements ICustomerController {
	@Autowired
	
	private ICustomerService customerService;

	@Override
	@GetMapping({"/customerListGet"}) /*hay que asegurarse que el boton que vamos a pinchar tenga este enlace para que se conecte con este método*/
	public String customerListGet(Principal principal,Model model) {
		System.out.println("TRAZA customerListGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("userPicture", "");
		
		
		//inyectar los registros de los customer, siempre debemos tirar de los servicios. aca hemos pedido la lista
		
		model.addAttribute("customerList", this.customerService.findAll());
		
		return "customerList";
	}

}
