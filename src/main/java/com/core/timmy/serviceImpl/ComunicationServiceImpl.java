package com.core.timmy.serviceImpl;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.timmy.data.model.Comunication;

import com.core.timmy.data.repository.IComunicationRepository;
import com.core.timmy.service.IComunicationService;
import com.core.timmy.service.ICustomerService;


@Service
public class ComunicationServiceImpl implements IComunicationService {
	
	@Autowired
	private IComunicationRepository comunicationRepository;

	@Override
	public Comunication save(Comunication comunication) {
		
		return this.comunicationRepository.save(comunication);
		
	}

	@Override
	public Optional<Comunication> findById(Integer id) {
		
		
		return this.comunicationRepository.findById(id);
		
	}

	@Override
	public List<Comunication> findAll() {
		
		return this.comunicationRepository.findAll(); /*repositorio devuelve un objeto list, la mayoria de veces array list. 
		en list el orden importa, acepta duplicado*/
	}
	
	

}
