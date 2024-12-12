package com.core.timmy.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.timmy.data.model.Contact;

import com.core.timmy.data.repository.IContactRepository;

import com.core.timmy.service.IContactService;




@Service
public class ContactServiceImpl implements IContactService {
	
	@Autowired
	private IContactRepository contactRepository;

	@Override
	public Contact save(Contact customerContact) {
		
		return this.contactRepository.save(customerContact);
		
	}
	
	@Override
	public Boolean deleteById(Long id) {
		
		this.contactRepository.deleteById(id);
		return !this.contactRepository.existsById(id);
		
		
	}
	
	@Override
	public Contact newContact() {
		
		Contact Contact = new Contact();
		Contact.setId(0L);
		
		return Contact;
		
	}

	@Override
	public Optional<Contact> findById(Long id) {
		
		
		return this.contactRepository.findById(id);
		
	}

	@Override
	public List<Contact> findAll() {
		
		return this.contactRepository.findAll(); /*repositorio devuelve un objeto list, la mayoria de veces array list. 
		en list el orden importa, acepta duplicado*/
	}

	@Override
	public List<Contact> findByName(String name) {
		
		return this.contactRepository.findByName(name);
	}

	@Override
	public List<Contact> findByNameOrderByEmailDesc(String name) {
		
		return this.contactRepository.findByNameOrderByEmailDesc(name);
	}

	@Override
	public List<Contact> findByNameLike(String name) {
		
		return this.contactRepository.findByNameLike( name);
	}

	@Override
	public List<Contact> findByNameAndPhone(String name, String phone) {
		// TODO Auto-generated method stub
		return this.contactRepository.findByNameAndPhone(name, phone);
	}

	@Override
	public List<Contact> findByIdBetween(Long start, Long end) {
		// TODO Auto-generated method stub
		return this.contactRepository.findByIdBetween( start, end);
	}

	@Override
	public List<Contact> findByIdLessThan(Long id) {
		// TODO Auto-generated method stub
		return this.contactRepository.findByIdLessThan( id);
	}

	@Override
	public List<Contact> findByObservationsContaining(String content) {
		// TODO Auto-generated method stub
		return this.contactRepository.findByObservationsContaining( content);
	}

	@Override
	public List<Contact> findByObservationsIn(Collection<String> searchingWords) {
		// TODO Auto-generated method stub
		return this.contactRepository.findByObservationsIn( searchingWords);
	}

	@Override
	public List<Contact> findByPersonOfInterestName(String personOfInterestName) {
		// TODO Auto-generated method stub
		return this.contactRepository.findByPersonOfInterestName(personOfInterestName);
	}
	
	

}
