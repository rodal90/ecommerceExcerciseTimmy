package com.core.timmy.controllerImpl;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.core.timmy.controller.IContactController;
import com.core.timmy.controller.IStartController;

@Controller
public class ContactControllerImpl implements IContactController {

	@Override
	@GetMapping({"/contactListGet"}) /*hay que asegurarse que el boton que vamos a pinchar tenga este enlace para que se conecte con este método*/
	public String contactListGet(Principal principal,Model model) {//que es principal?
		System.out.println("TRAZA contactListGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("userPicture", "");
		return "contactList";
	}

}
