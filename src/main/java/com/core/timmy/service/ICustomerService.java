package com.core.timmy.service;

import org.springframework.stereotype.Service;

import com.core.timmy.data.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface ICustomerService {
	
	public Customer save(Customer customer);
	
	public Optional<Customer> findById(Long id);
	
	public List<Customer> findAll();

	public String deleteById(Long id);

	public Customer newCustomer();

	public String findByNameLike(String string);

}

