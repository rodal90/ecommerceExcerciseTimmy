package com.core.timmy.controller;

import java.security.Principal;

import org.springframework.ui.Model;

public interface IProviderController {

	 public String providerListGet(Principal principal, Model model);

}
