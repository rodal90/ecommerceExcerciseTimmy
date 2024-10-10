package com.core.timmy.service;

import org.springframework.stereotype.Service;

import com.core.timmy.data.model.Role;

import java.util.Optional;
import java.util.Set;

@Service
public interface IRoleService {
	
	public Role save(Role role);
	
	public Optional<Role> findById(String rolename);
	
	public Set<Role> findall();

}
