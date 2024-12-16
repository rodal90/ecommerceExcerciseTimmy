package com.core.timmy.controllerImpl;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.core.timmy.controller.IContactController;
import com.core.timmy.data.model.Contact;
import com.core.timmy.service.IContactService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ContactControllerImpl 
extends MasterControllerImpl
implements IContactController {

	
	@Autowired
	private IContactService service; //customerContactService
	
	
	@Override
	@GetMapping({ "/contact/updateGet/{id}" }) /*
												 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace
												 * para que se conecte con este método
												 */
	public String contactUpdateGet(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
		log.info("TRAZA contactUpdateGet");
		
		  this.injectCommonAtrributesInHtmlPage(principal, model,request);

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		/* log.info("customer= " + this.customerService.findById(id)); */

		model.addAttribute("entity", this.service.findById(id).get());

		return "contact/contactUpdate";
	}
	
	@Override
	@PostMapping({ "/contact/updatePost" }) /*
												 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace
												 * para que se conecte con este método
												 */
	public String contactUpdatePost(@Valid Contact contact, BindingResult bindingResult, Principal principal,
			Model model, HttpServletRequest request) {
		log.info("TRAZA contactUpdatePost");

		if (bindingResult.hasErrors()) {
			log.error("el formulario de contact tiene errores" + bindingResult.getAllErrors());
			
			model.addAttribute("entity", contact);

			return "contact/contactUpdate";

		} else {

			log.info("Formulario correcto: " + contact);
			
			this.service.save(contact);
			
			log.warn("TRAZA: después de salir de contactPost >> contactService -> save");

			return "redirect:/contactListGet";

		}

	}
	
	
	@Override
	@GetMapping({ "/contact/deleteGet/{id}" }) /*
												 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace
												 * para que se conecte con este método
												 */
	public String contactDeleteGet(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
	    log.info("TRAZA contactDeleteGet");
	    
	    this.injectCommonAtrributesInHtmlPage(principal, model,request);

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		log.info("contact= " + this.service.findById(id).get());

		model.addAttribute("entity", this.service.findById(id).get());

		return "contact/contactDelete";
	}

	@Override
	@GetMapping({ "/contact/deleteConfirmed/{id}" }) /*
														 * hay que asegurarse que el boton que vamos a pinchar tenga
														 * este enlace para que se conecte con este método
														 */
	public String contactDeleteConfirmed(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
		log.info("TRAZA providerDeleteConfirmed");
		
		 this.injectCommonAtrributesInHtmlPage(principal, model,request);

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		log.info("contact deleted= " + this.service.deleteById(id));

		return "redirect:/contactListGet";
	}
	
	@Override
	@GetMapping({ "/contact/viewGet/{id}" }) /*
												 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace
												 * para que se conecte con este método
												 */
	public String contactViewGet(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA contactViewGet");
		
		this.injectCommonAtrributesInHtmlPage(principal, model,request);
		

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		log.info("contact= " + this.service.findById(id));

		model.addAttribute("entity", this.service.findById(id).get());

		return "contact/contactView";
	}
	
	@Override
	@GetMapping({"/contactListGet"}) /*hay que asegurarse que el boton que vamos a pinchar tenga este enlace para que se conecte con este método*/
	public String contactListGet(
			Principal principal,
			Model model,
			HttpServletRequest request) {//que es principal?
		//log.info("Customer contact id=1: " + this.service.findById(1L));
		
		
		
		this.injectCommonAtrributesInHtmlPage(principal, model,request);
		
		model.addAttribute("entityList", this.service.findAll());
		
		//Testing some Service Methods
		/*log.info("Contactos que contengan Gómez en el name: " + this.service.findByNameLike("%Gómez%"));
		
		log.info("Nombres de los contactos ordenados por correo electrónico: " + this.service.findByNameOrderByEmailDesc("%Gómez%"));
		
		log.info("Nombres y Teléfono: " + this.service.findByNameAndPhone("Sergio Álvarez", "+34 622 123 987"));
		
		//Ejemplo de que muestre entre rangos de info.;
		
		log.info("IDs entre 4 y 10 : " + this.service.findByIdBetween(4L, 10L));*/
			
		
		
		return "contact/contactList";
	}
	
	

}
