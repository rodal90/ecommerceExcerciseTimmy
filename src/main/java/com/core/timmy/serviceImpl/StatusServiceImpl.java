package com.core.timmy.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.core.timmy.data.model.Status;
import com.core.timmy.data.repository.IStatusRepository;
import com.core.timmy.service.IStatusService;



@Service
public class StatusServiceImpl implements IStatusService {
	
	@Autowired
	private IStatusRepository statusRepository;

	@Override
	public Status save(Status status) {
		
		return this.statusRepository.save(status);
		
	}

	@Override
	public Optional<Status> findById(String statusName) {
		
		
		return this.statusRepository.findById(statusName);
		
	}

	@Override
	public List<Status> findall() {
		
		return this.statusRepository.findAll(); /*repositorio devuelve un objeto list, la mayoria de veces array list. 
		en list el orden importa, acepta duplicado*/
	}
	
	

}
