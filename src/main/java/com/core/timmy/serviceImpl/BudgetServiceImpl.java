package com.core.timmy.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.core.timmy.data.model.Budget;
import com.core.timmy.data.repository.IBudgetRepository;

import com.core.timmy.service.IBudgetService;




@Service
public class BudgetServiceImpl implements IBudgetService {
	
	@Autowired
	private IBudgetRepository budgetRepository;

	@Override
	public Budget save(Budget budget) {
		
		return this.budgetRepository.save(budget);
	}

	@Override
	public Optional<Budget> findById(Integer id) {
		
		return this.budgetRepository.findById(id);
		
	}

	@Override
	public List<Budget> findall() {
		
		return this.budgetRepository.findAll(); /*repositorio devuelve un objeto list, la mayoria de veces array list. 
		en list el orden importa, acepta duplicado*/
	}
	
	

}
