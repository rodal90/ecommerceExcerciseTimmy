package com.core.timmy.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
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

	@Override
	public List<CustomerContact> findByName(String name) {
		// TODO Auto-generated method stub
		return this.customerContactRepository.findByName(name);
	}

	@Override
	public List<CustomerContact> findByNameOrderByEmailDesc(String name) {
		// TODO Auto-generated method stub
		return this.customerContactRepository.findByNameOrderByEmailDesc(name);
	}

	@Override
	public List<CustomerContact> findByNameLike(String name) {
		// TODO Auto-generated method stub
		return this.customerContactRepository.findByNameLike( name);
	}

	@Override
	public List<CustomerContact> findByNameAndPhone(String name, String phone) {
		// TODO Auto-generated method stub
		return this.customerContactRepository.findByNameAndPhone(name, phone);
	}

	@Override
	public List<CustomerContact> findByIdBetween(Integer start, Integer end) {
		// TODO Auto-generated method stub
		return this.customerContactRepository.findByIdBetween( start, end);
	}

	@Override
	public List<CustomerContact> findByIdLessThan(Integer id) {
		// TODO Auto-generated method stub
		return this.customerContactRepository.findByIdLessThan( id);
	}

	@Override
	public List<CustomerContact> findByObservationsContaining(String content) {
		// TODO Auto-generated method stub
		return this.customerContactRepository.findByObservationsContaining( content);
	}

	@Override
	public List<CustomerContact> findByObservationsIn(Collection<String> searchingWords) {
		// TODO Auto-generated method stub
		return this.customerContactRepository.findByObservationsIn( searchingWords);
	}

	@Override
	public List<CustomerContact> findByCustomerName(String customerName) {
		// TODO Auto-generated method stub
		return this.customerContactRepository.findByCustomerName(customerName);
	}
	
	

}
