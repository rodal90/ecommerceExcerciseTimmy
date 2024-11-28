package com.core.timmy.serviceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.timmy.data.model.Provider;
import com.core.timmy.data.repository.IProviderRepository;


import com.core.timmy.service.IProviderService;



@Service
public class ProviderServiceImpl implements IProviderService {
	
	@Autowired
	private IProviderRepository providerRepository;

	@Override
	public Provider save(Provider provider ) {
		
		return this.providerRepository.save(provider);
		
	}

	@Override
	public Optional<Provider> findById(Long id) {
		
		
		return this.providerRepository.findById(id);
		
	}

	@Override
	public List<Provider> findAll() {
		
		return this.providerRepository.findAll(); /*repositorio devuelve un objeto list, la mayoria de veces array list. 
		en list el orden importa, acepta duplicado*/
	}

	@Override
	public String deleteById(Long id) {
		this.providerRepository.deleteById(id);		
		return this.providerRepository.existsById(id)? "Borrado" : "No Borrado";
	}

	@Override
	public Provider newProvider() {
		return new Provider();
	}

	@Override
	public String findByNameLike(String string) {
	
		return null;
	}


	@Override
	public List<Provider> findAllByOrderByName() {
		// TODO Auto-generated method stub
		return this.providerRepository.findAll()
				.stream()
				.sorted(Comparator.comparing(Provider::getName))
			    .collect(Collectors.toList());
				
		//return this.providerRepository.findAllByOrderByName();
	}
	
	

}
