package com.core.timmy.controllerImpl;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.core.timmy.controller.IEncryptionController;
import com.core.timmy.data.model.Encryption;
import com.core.timmy.service.IEncryptionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EncryptionControllerImpl extends MasterControllerImpl implements IEncryptionController {
	@Autowired

	private IEncryptionService encryptionService;
	
//si no ponemos @entity no lo guardamos en base de datos
	//login y encryption nos interes usarlos como objeto, no lo guardamos en base de datos
	//creamos un modelo de datos para la pagina html, pero solo nos devuelve un objeto

	@Override
	@GetMapping({ "/encryption/encryptionGet" }) 
	public String encryptionGet(Principal principal, Model model, HttpServletRequest request) {
		log.info("TRAZA encryptionGet");
		//ESTA SON LAS INYECCINOES
		
		this.injectCommonAtrributesInHtmlPage(principal, model,request);

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista
//en el fomulario hemos llamado al objeto: "objeto encryption"
		//preferimos que lo haga el servicio, por si hay que meter datos iniciales.
		model.addAttribute("encryption", this.encryptionService.newEntity());
		   //inyectamos el objeto encoderList
		model.addAttribute("encoderList", this.encryptionService.findAllEncodersName()); //de donde lo voy a sacar de encryptionService
		//sacamos los nombres nada más porque ya los habiamos sacado y los habiamos metido en esta lista.

		return "encryption/encryptionGet";
	}
	
	
	
	
	@Override
	@PostMapping({ "/encryption/encryptionPost" }) /*
												 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace
												 * para que se conecte con este método
												 */
	public String encryptionPost(@Valid Encryption encryption, BindingResult bindingResult, Principal principal,
			Model model, HttpServletRequest request) {
		System.out.println("TRAZA encryptionPost");

		if (bindingResult.hasErrors()) {
			log.error("el formulario de encryption tiene errores" + bindingResult.getAllErrors());

			return "redirect:/encryption/encryptionGet"; // Redirect the manda a un mapeo especifico en este caso directo al controlador
			
		} else {

			log.info("Formulario correcto: " + encryption);
			//la acción que queremos que haga es encryptar, anteriormente queriamos guardar lo tenemos con guardar a los otros
			encryption = this.encryptionService.encryptText(encryption);
			log.warn("TRAZA: después de salir de encryptionPost >> EncryptionService -> encryptText");

			return "encryption/encryptionPost"; //le enviaremos a un html con el resultado de la encryptación.

		}
	}
}
