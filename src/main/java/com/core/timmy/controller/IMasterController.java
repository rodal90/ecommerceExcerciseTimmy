package com.core.timmy.controller;

import java.security.Principal;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

public interface IMasterController {
	
	public void injectCommonAtrributesInHtmlPage(			
			Principal principal, 
			Model model, 
			HttpServletRequest request);

}
