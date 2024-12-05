package com.core.timmy.controllerImpl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.core.timmy.controller.ICustomerController;
import com.core.timmy.controller.IStartController;
import com.core.timmy.data.model.Customer;
import com.core.timmy.service.ICustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CustomerControllerImpl implements ICustomerController {
	@Autowired

	private ICustomerService customerService;

	@Override
	@GetMapping({ "/customerListGet" }) /*
										 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace para
										 * que se conecte con este método
										 */
	public String customerListGet(Principal principal, Model model ,HttpServletRequest request) {
		System.out.println("TRAZA customerListGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("userPicture", "");

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		model.addAttribute("customerList", this.customerService.findAll());

		return "customer/customerList";
	}

	/*
	 * un path variable se puede utilizar poniendo dentro de corchetes lo que se va
	 * ir modificando
	 */
	/*
	 * pueden haber mas etiquetas de path variables que podemos guardr en variables
	 * como el Long id
	 */

	@Override
	@GetMapping({ "/customer/viewGet/{id}" }) /*
												 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace
												 * para que se conecte con este método
												 */
	public String customerViewGet(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA customerViewGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("userPicture", "");

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		log.info("customer= " + this.customerService.findById(id));

		model.addAttribute("customer", this.customerService.findById(id).get());

		return "customer/customerView";
	}

	@Override
	@GetMapping({ "/customer/deleteGet/{id}" }) /*
												 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace
												 * para que se conecte con este método
												 */
	public String customerDeleteGet(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA customerDeleteGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("userPicture", "");

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		log.info("customer= " + this.customerService.findById(id).get());

		model.addAttribute("customer", this.customerService.findById(id).get());

		return "customer/customerDelete";
	}

	@Override
	@GetMapping({ "/customer/deleteConfirmed/{id}" }) /*
														 * hay que asegurarse que el boton que vamos a pinchar tenga
														 * este enlace para que se conecte con este método
														 */
	public String customerDeleteConfirmed(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA customerDeleteConfirmed");
		model.addAttribute("username", principal.getName());
		model.addAttribute("userPicture", "");

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		log.info("customer deleted= " + this.customerService.deleteById(id));

		return "redirect:/customerListGet";
	}

	@Override
	@GetMapping({ "/customer/updateGet/{id}" }) /*
												 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace
												 * para que se conecte con este método
												 */
	public String customerUpdateGet(@PathVariable("id") Long id, Principal principal, Model model,
			HttpServletRequest request) {
		System.out.println("TRAZA customerUpdateGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("userPicture", "");

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		/* log.info("customer= " + this.customerService.findById(id)); */

		model.addAttribute("customer", this.customerService.findById(id).get());

		return "customer/customerUpdate";
	}

	@Override
	@GetMapping({ "/customer/addGet" }) /*
										 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace para
										 * que se conecte con este método
										 */
	public String customerAddGet(Principal principal, Model model, HttpServletRequest request) {
		System.out.println("TRAZA customerAddGet");
		model.addAttribute("username", principal.getName());
		model.addAttribute("userPicture", "");

		// inyectar los registros de los customer, siempre debemos tirar de los
		// servicios. aca hemos pedido la lista

		model.addAttribute("customer", this.customerService.newCustomer());

		return "customer/customerAdd";
	}

	@Override
	@PostMapping({ "/customer/addPost" }) /*
											 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace
											 * para que se conecte con este método
											 */
	public String customerAddPost(@Valid Customer customer, BindingResult bindingResult, Principal principal,
			Model model, HttpServletRequest request) {
		System.out.println("TRAZA customerAddPost");

		// comprueba las validaciones, vemos que error y luego reemenviarle a donde
		if (bindingResult.hasErrors()) {
			log.error("el formulario de customer tiene errores" + bindingResult.getAllErrors());

			return "redirect:/customer/addGet"; // Redirect the manda a un mapeo especifico

		} else {

			log.info("Formulario correcto: " + customer);
			this.customerService.save(customer);
         // me regresa a la lista customer
			return "redirect:/customerListGet";

		}

	}

	@Override
	@PostMapping({ "/customer/updatePost" }) /*
												 * hay que asegurarse que el boton que vamos a pinchar tenga este enlace
												 * para que se conecte con este método
												 */
	public String customerUpdatePost(@Valid Customer customer, BindingResult bindingResult, Principal principal,
			Model model, HttpServletRequest request) {
		System.out.println("TRAZA customerUpdatePost");

		if (bindingResult.hasErrors()) {
			log.error("el formulario de customer tiene errores" + bindingResult.getAllErrors());

			return "redirect:/customer/updateGet/" + customer.getId(); // Redirect the manda a un mapeo especifico

		} else {

			log.info("Formulario correcto: " + customer);
			this.customerService.save(customer);

			return "redirect:/customerListGet";

		}

	}


}
