package com.core.timmy.service;

import org.springframework.stereotype.Service;

import com.core.timmy.data.model.CustomerContact;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface ICustomerContactService {
	
	public CustomerContact save(CustomerContact customerContact);
	
	public Optional<CustomerContact> findById(Integer id);
	
	public List<CustomerContact> findAll();

}
