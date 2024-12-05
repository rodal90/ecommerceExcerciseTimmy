package com.core.timmy.controllerImpl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.core.timmy.controller.ICustomerContactController;
import com.core.timmy.controller.IStartController;
import com.core.timmy.data.model.CustomerContact;
import com.core.timmy.data.model.Provider;
import com.core.timmy.service.ICustomerContactService;
import com.core.timmy.service.ICustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CustomerContactControllerImpl 
extends MasterControllerImpl
implements ICustomerContactController {

	
	@Autowired
	private ICustomerContactService service; //customerContactService
	
	
	@Override
	@GetMapping({ "/customerContact/updateGet/{id}" }) /*
												 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace
												 * para que se conecte con este método
												 */
	public String customerContactUpdateGet(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
		log.info("TRAZA customerContactUpdateGet");
		
		  this.injectCommonAtrributesInHtmlPage(principal, model,request);

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		/* log.info("customer= " + this.customerService.findById(id)); */

		model.addAttribute("entity", this.service.findById(id).get());

		return "customerContact/customerContactUpdate";
	}
	
	@Override
	@PostMapping({ "/customerContact/updatePost" }) /*
												 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace
												 * para que se conecte con este método
												 */
	public String customerContactUpdatePost(@Valid CustomerContact customerContact, BindingResult bindingResult, Principal principal,
			Model model, HttpServletRequest request) {
		log.info("TRAZA customerContactUpdatePost");

		if (bindingResult.hasErrors()) {
			log.error("el formulario de customerContact tiene errores" + bindingResult.getAllErrors());
			
			model.addAttribute("entity", customerContact);

			return "customerContact/customerContactUpdate";

		} else {

			log.info("Formulario correcto: " + customerContact);
			
			this.service.save(customerContact);
			
			log.warn("TRAZA: después de salir de customerContactPost >> customerContactService -> save");

			return "redirect:/customerContactListGet";

		}

	}
	
	
	@Override
	@GetMapping({ "/customerContact/deleteGet/{id}" }) /*
												 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace
												 * para que se conecte con este método
												 */
	public String customerContactDeleteGet(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
	    log.info("TRAZA customerContactDeleteGet");
	    
	    this.injectCommonAtrributesInHtmlPage(principal, model,request);

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		log.info("customerContact= " + this.service.findById(id).get());

		model.addAttribute("entity", this.service.findById(id).get());

		return "customerContact/customerContactDelete";
	}

	@Override
	@GetMapping({ "/customerContact/deleteConfirmed/{id}" }) /*
														 * hay que asegurarse que el boton que vamos a pinchar tenga
														 * este enlace para que se conecte con este método
														 */
	public String customerContactDeleteConfirmed(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
		log.info("TRAZA providerDeleteConfirmed");
		
		 this.injectCommonAtrributesInHtmlPage(principal, model,request);

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		log.info("customerContact deleted= " + this.service.deleteById(id));

		return "redirect:/customerContactListGet";
	}
	
	@Override
	@GetMapping({ "/customerContact/viewGet/{id}" }) /*
												 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace
												 * para que se conecte con este método
												 */
	public String customerContactViewGet(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA customerContactViewGet");
		
		this.injectCommonAtrributesInHtmlPage(principal, model,request);
		

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		log.info("customerContact= " + this.service.findById(id));

		model.addAttribute("entity", this.service.findById(id).get());

		return "customerContact/customerContactView";
	}
	
	@Override
	@GetMapping({"/customerContactListGet"}) /*hay que asegurarse que el boton que vamos a pinchar tenga este enlace para que se conecte con este método*/
	public String customerContactListGet(
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
			
		
		
		return "customerContact/customerContactList";
	}
	
	

}
