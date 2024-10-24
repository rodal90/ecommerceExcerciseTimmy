package com.core.timmy.controllerImpl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.core.timmy.controller.ICustomerController;
import com.core.timmy.controller.IStartController;

@Controller
public class CustomerControllerImpl implements ICustomerController {

	@Override
	@GetMapping({"/customerListGet"}) /*hay que asegurarse que el boton que vamos a pinchar tenga este enlace para que se conecte con este método*/
	public String customerListGet(Model model) {
		System.out.println("TRAZA customerListGet");
		return "customerList";
	}

}
