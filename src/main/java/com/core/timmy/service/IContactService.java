package com.core.timmy.service;

import org.springframework.stereotype.Service;

import com.core.timmy.data.model.Contact;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface IContactService {
	
	public Contact save(Contact contact);
	
	public Optional<Contact> findById(Long id);
	
	public List<Contact> findAll();
	
	
	public List<Contact> findByName(String name);
	public List<Contact> findByNameOrderByEmailDesc(String name);//el Desc es para que ordene los datos de forma descendente
	public List<Contact> findByNameLike(String name);
	public List<Contact> findByNameAndPhone(String name, String phone);
	public List<Contact> findByIdBetween(Long start, Long end); 
	public List<Contact> findByIdLessThan(Long id); 
	public List<Contact> findByObservationsContaining(String content); 
	
	public List<Contact> findByObservationsIn(Collection<String>searchingWords);
	
	//como hacer joins con JPA
	
	public List<Contact>findByPersonOfInterestName(String personOfInterestName);

	public Boolean deleteById(Long id);

	public Contact newContact();

}
