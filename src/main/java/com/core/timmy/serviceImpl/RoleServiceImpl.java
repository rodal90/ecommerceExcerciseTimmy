package com.core.timmy.serviceImpl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.timmy.data.model.Role;
import com.core.timmy.data.repository.IRoleRepository;
import com.core.timmy.service.IRoleService;
@Service
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private IRoleRepository roleRepository;

	@Override
	public Role save(Role role) {
		
		return this.roleRepository.save(role);
		
	}

	@Override
	public Optional<Role> findById(String rolename) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Set<Role> findall() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
