package com.core.timmy.controller;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.core.timmy.data.model.Customer;
import com.core.timmy.data.model.Provider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

public interface IProviderController {

	 public String providerListGet(Principal principal, Model model, HttpServletRequest request);

	public String providerViewGet(Long id, Principal principal, Model model, HttpServletRequest request);

	public String providerDeleteGet(Long id, Principal principal, Model model, HttpServletRequest request);

	public String providerDeleteConfirmed(Long id, Principal principal, Model model, HttpServletRequest request);

	public String providerAddGet(Principal principal, Model model, HttpServletRequest request);

	public String providerUpdateGet(Long id, Principal principal, Model model, HttpServletRequest request);

	public String providerUpdatePost(@Valid Provider provider, BindingResult bindingResult, Principal principal, Model model,
			HttpServletRequest request);

	public String providerAddPost(@Valid Provider provider, BindingResult bindingResult, Principal principal, Model model,
			HttpServletRequest request);

}
