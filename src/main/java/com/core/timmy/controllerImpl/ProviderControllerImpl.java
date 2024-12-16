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
public class ProviderControllerImpl extends MasterControllerImpl implements IProviderController {
	@Autowired

	private IProviderService providerService;
	
	@Override
	@GetMapping({ "/provider/viewGet/{id}" }) /*
												 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace
												 * para que se conecte con este método
												 */
	public String providerViewGet(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA providerViewGet");
		
		this.injectCommonAtrributesInHtmlPage(principal, model,request);

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		model.addAttribute("provider", this.providerService.findById(id).get());

		return "provider/providerView";
	}



	@Override
	@GetMapping({ "/providerListGet" }) 
	public String providerListGet(Principal principal, Model model, HttpServletRequest request) {
		log.info("TRAZA providerListGet");
		
		
		this.injectCommonAtrributesInHtmlPage(principal, model,request);

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		model.addAttribute("providerList", this.providerService.findAll()
		    .stream()
		    .sorted(Comparator.comparing(Provider::getName))
		    .collect(Collectors.toList())
		    );
		
		//model.addAttribute("providerList", this.providerService.findAllByOrderByName());

		return "provider/providerList";
	}
	
	@Override
	@GetMapping({ "/provider/deleteGet/{id}" }) /*
												 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace
												 * para que se conecte con este método
												 */
	public String providerDeleteGet(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA providerDeleteGet");
		
		this.injectCommonAtrributesInHtmlPage(principal, model,request);

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		log.info("provider= " + this.providerService.findById(id).get());

		model.addAttribute("provider", this.providerService.findById(id).get());

		return "provider/providerDelete";
	}

	@Override
	@GetMapping({ "/provider/deleteConfirmed/{id}" }) /*
														 * hay que asegurarse que el boton que vamos a pinchar tenga
														 * este enlace para que se conecte con este método
														 */
	public String providerDeleteConfirmed(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA providerDeleteConfirmed");
		model.addAttribute("username", principal.getName());
		model.addAttribute("userPicture", "");

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		log.info("provider deleted= " + this.providerService.deleteById(id));

		return "redirect:/providerListGet";
	}
	
	@Override
	@GetMapping({ "/provider/updateGet/{id}" }) /*
												 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace
												 * para que se conecte con este método
												 */
	public String providerUpdateGet(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA providerUpdateGet");
		
		this.injectCommonAtrributesInHtmlPage(principal, model,request);

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		/* log.info("customer= " + this.customerService.findById(id)); */

		model.addAttribute("provider", this.providerService.findById(id).get());

		return "provider/providerUpdate";
	}
	
	@Override
	@PostMapping({ "/provider/updatePost" }) /*
												 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace
												 * para que se conecte con este método
												 */
	public String providerUpdatePost(@Valid Provider provider, BindingResult bindingResult, Principal principal,
			Model model, HttpServletRequest request) {
		System.out.println("TRAZA providerUpdatePost");

		if (bindingResult.hasErrors()) {
			log.error("el formulario de provider tiene errores" + bindingResult.getAllErrors());

			return "redirect:/provider/updateGet/" + provider.getId(); // Redirect the manda a un mapeo especifico

		} else {

			log.info("Formulario correcto: " + provider);
			
			this.providerService.save(provider);
			log.warn("TRAZA: después de salir de providerUpdatePost >> ProviderService -> save");

			return "redirect:/providerListGet";

		}

	}

	@Override
	@GetMapping({ "/provider/addGet" }) /*
										 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace para
										 * que se conecte con este método
										 */
	public String providerAddGet(Principal principal, Model model, HttpServletRequest request) {
		System.out.println("TRAZA providerAddGet");
		
		
		this.injectCommonAtrributesInHtmlPage(principal, model,request);

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		model.addAttribute("provider", this.providerService.newProvider());

		return "provider/providerAdd";
	}

	@Override
	@PostMapping({ "/provider/addPost" }) /*
											 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace
											 * para que se conecte con este método
											 */
	public String providerAddPost(@Valid Provider provider, BindingResult bindingResult, Principal principal,
			Model model, HttpServletRequest request) {
		System.out.println("TRAZA providerAddPost");

		// comprueba las validaciones, vemos que error y luego reemenviarle a donde
		if (bindingResult.hasErrors()) {
			log.error("el formulario de provider tiene errores" + bindingResult.getAllErrors());

			return "provider/providerAdd"; // Redirect the manda a un mapeo especifico

		} else {

			log.info("Formulario correcto: " + provider);
		
			this.providerService.save(provider);
			log.warn("TRAZA: después de salir de providerAddPost >> ProviderService -> save");
         // me regresa a la lista customer
			return "redirect:/providerListGet";

		}

	}
	
}
