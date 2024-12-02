package com.core.timmy.controllerImpl;

import java.security.Principal;

import org.springframework.ui.Model;

import com.core.timmy.controller.IMasterController;

import jakarta.servlet.http.HttpServletRequest;

public class MasterControllerImpl implements IMasterController {

	@Override
	public void injectCommonAtrributesInHtmlPage(
			Principal principal,
			Model model,
			HttpServletRequest request) {
	

	}

}
