package com.core.timmy.service;

import org.springframework.stereotype.Service;

import com.core.timmy.data.model.CustomerContact;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface ICustomerContactService {
	
	public CustomerContact save(CustomerContact customerContact);
	
	public Optional<CustomerContact> findById(Long id);
	
	public List<CustomerContact> findAll();
	
	
	public List<CustomerContact> findByName(String name);
	public List<CustomerContact> findByNameOrderByEmailDesc(String name);//el Desc es para que ordene los datos de forma descendente
	public List<CustomerContact> findByNameLike(String name);
	public List<CustomerContact> findByNameAndPhone(String name, String phone);
	public List<CustomerContact> findByIdBetween(Long start, Long end); 
	public List<CustomerContact> findByIdLessThan(Long id); 
	public List<CustomerContact> findByObservationsContaining(String content); 
	
	public List<CustomerContact> findByObservationsIn(Collection<String>searchingWords);
	
	//como hacer joins con JPA
	
	public List<CustomerContact>findByCustomerName(String customerName);

	public Boolean deleteById(Long id);

	public CustomerContact newCustomerContact();

}
