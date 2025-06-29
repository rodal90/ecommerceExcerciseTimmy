package com.core.timmy.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.core.timmy.data.model.User;
import java.util.Optional;
import java.util.Set;

@Service
public interface IUserService extends UserDetailsService {
	
	public User save(User user);
	
	public Optional<User> findById(String username);
	
	public Set<User> findall();

	
	User newEntity();

}
