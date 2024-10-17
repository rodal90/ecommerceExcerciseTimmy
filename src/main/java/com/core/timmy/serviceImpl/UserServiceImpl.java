package com.core.timmy.serviceImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.timmy.data.model.User;
import com.core.timmy.data.repository.IUserRepository;
import com.core.timmy.service.IUserService;



@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public User save(User user) {
		
		return this.userRepository.save(user);
		
	}

	@Override
	public Optional<User> findById(String username) {
		
		
		return this.userRepository.findById(username);
		
	}

	@Override
	public Set<User> findall() {
		
		return new HashSet<User>(this.userRepository.findAll()); /*repositorio devuelve un objeto list, la mayoria de veces array list. 
		en list el orden importa, acepta duplicado*/
	}
	
	

}
