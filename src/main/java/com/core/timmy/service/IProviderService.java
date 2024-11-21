package com.core.timmy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.core.timmy.data.model.Provider;

@Service
public interface IProviderService {

	public Provider save(Provider Provider);

	public Optional<Provider> findById(Long id);

	public List<Provider> findAll();

	public String deleteById(Long id);

	public Provider newProvider();

	public String findByNameLike(String string);
	
	public List<Provider> findAllByOrderByName();

}
