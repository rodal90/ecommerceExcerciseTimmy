package com.core.timmy.service;

import org.springframework.stereotype.Service;

import com.core.timmy.data.model.Comunication;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface IComunicationService {
	
	public Comunication save(Comunication comunication);
	
	public Optional<Comunication> findById(Long id);
	
	public List<Comunication> findAll();

}

