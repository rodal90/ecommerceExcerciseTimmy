package com.core.timmy.controllerImpl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.core.timmy.controller.IStartController;

@Controller
public class StartControllerImpl implements IStartController {

	@Override
	@GetMapping({"/loginGet"}) /*hay que asegurarse que el boton que vamos a pinchar tenga este enlace para que se conecte con este método*/
	
	public String loginGet(Model model) {
		
		System.out.println("TRAZA loginGet");
		
		return "loginPage";
	}

	@Override
	@PostMapping({"/loginPost"})
	public String loginPost(Model model) {
		System.out.println("TRAZA loginPost");
		//TODO user/password test
		return "masterfull";
	}

}
