package com.core.timmy.controller;

import java.security.Principal;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

public interface IProviderController {

	 public String providerListGet(Principal principal, Model model);

	public String providerViewGet(Long id, Principal principal, Model model, HttpServletRequest request);

}
