package com.core.timmy.service;
//tampoco tiene repositorio ni modelo

import org.springframework.stereotype.Service;

import com.core.timmy.data.model.Login;


@Service
public interface ILoginService{
	

	public Login newEntity();

}
