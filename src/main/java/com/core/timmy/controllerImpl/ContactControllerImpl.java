package com.core.timmy.controllerImpl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.core.timmy.controller.IContactController;
import com.core.timmy.controller.IStartController;
import com.core.timmy.data.model.CustomerContact;
import com.core.timmy.service.ICustomerContactService;
import com.core.timmy.service.ICustomerService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ContactControllerImpl implements IContactController {

	
	@Autowired
	private ICustomerContactService customerContactService;
	
	@Override
	@GetMapping({"/contactListGet"}) /*hay que asegurarse que el boton que vamos a pinchar tenga este enlace para que se conecte con este método*/
	public String contactListGet(Principal principal,Model model) {//que es principal?
		System.out.println("TRAZA contactListGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("userPicture", "");
		
		//Testing some Service Methods
		log.info("Contactos que contengan Gómez en el name: " + this.customerContactService.findByNameLike("%Gómez%"));
		
		log.info("Nombres de los contactos ordenados por correo electrónico: " + this.customerContactService.findByNameOrderByEmailDesc("%Gómez%"));
		
		log.info("Nombres y Teléfono: " + this.customerContactService.findByNameAndPhone("Sergio Álvarez", "+34 622 123 987"));
		
		//Ejemplo de que muestre entre rangos de info.;
		
		log.info("IDs entre 4 y 10 : " + this.customerContactService.findByIdBetween(4, 10));
			
		
		
		return "contactList";
	}

}
