package com.core.timmy.serviceImpl;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.core.timmy.aop.LogExecutionTimeAnnotation;
import com.core.timmy.data.model.Customer;

import com.core.timmy.data.repository.ICustomerRepository;

import com.core.timmy.service.ICustomerService;


@Service
public class CustomerServiceImpl implements ICustomerService {
	
	@Autowired
	private ICustomerRepository customerRepository;

	@Override
	public Customer save(Customer customer) {
		
		return this.customerRepository.save(customer);
		
	}

	@Override
	public Optional<Customer> findById(Long id) {
		
		
		return this.customerRepository.findById(id);
		
	}

	@Override
	@LogExecutionTimeAnnotation
	public List<Customer> findAll() {
		
		return this.customerRepository.findAll(); /*repositorio devuelve un objeto list, la mayoria de veces array list. 
		en list el orden importa, acepta duplicado*/
	}

	@Override
	@Secured({"MANAGER"})
	public String deleteById(Long id) {
		this.customerRepository.deleteById(id);		
		return this.customerRepository.existsById(id)? "Borrado" : "No Borrado";
	}

	@Override
	public Customer newCustomer() {
		return new Customer();
	}

	@Override
	public String findByNameLike(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
