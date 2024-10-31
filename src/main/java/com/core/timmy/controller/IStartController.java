package com.core.timmy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.core.timmy.data.model.Login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/*en todos hay que importar algo para definirlos, con sus etiquetas ej: @Controller, @Service, @*/

@Controller
public interface IStartController {
	
	public String loginGet(Model model); /*el importe nos genera los controladores de Spring. El calss model contiene todos los parametros 
	que me interesa que contenga esa pagina, si quiero que aparezca un valor, todas la variables las puedo incluir en model para que la página 
	pueda absorber esos datos y mostrarmelo en la página*/


	public String loginPost(@Valid Login login, BindingResult bindingResult, HttpServletRequest request, Model model);


	String dumpDBGet();
}
