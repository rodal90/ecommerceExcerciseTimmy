package com.core.timmy.service;

import org.springframework.stereotype.Service;

import com.core.timmy.data.model.Status;

import java.util.Optional;
import java.util.List;

@Service
public interface IStatusService {
	
	public Status save(Status role);
	
	public Optional<Status> findById(Long id);
	
	//cuando la cardinalidade es de muchos a muchos  lo que se utiliza es colleciones de tipo Set,
	//pero cuando es de M:1 o 1:M lo que se usa es list.
	
	public List<Status> findall();

}
