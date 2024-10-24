package com.core.timmy.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.timmy.data.model.CustomerContact;

import com.core.timmy.data.repository.ICustomerContactRepository;

import com.core.timmy.service.ICustomerContactService;




@Service
public class CustomerContactServiceImpl implements ICustomerContactService {
	
	@Autowired
	private ICustomerContactRepository customerContactRepository;

	@Override
	public CustomerContact save(CustomerContact customerContact) {
		
		return this.customerContactRepository.save(customerContact);
		
	}

	@Override
	public Optional<CustomerContact> findById(Integer id) {
		
		
		return this.customerContactRepository.findById(id);
		
	}

	@Override
	public List<CustomerContact> findAll() {
		
		return this.customerContactRepository.findAll(); /*repositorio devuelve un objeto list, la mayoria de veces array list. 
		en list el orden importa, acepta duplicado*/
	}
	
	

}
