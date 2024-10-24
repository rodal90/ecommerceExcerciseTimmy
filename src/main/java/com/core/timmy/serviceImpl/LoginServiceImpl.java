package com.core.timmy.serviceImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.core.timmy.data.model.Login;
import com.core.timmy.data.model.User;
import com.core.timmy.data.repository.IUserRepository;
import com.core.timmy.service.ILoginService;
import com.core.timmy.service.IUserService;



@Service
public class LoginServiceImpl implements ILoginService {
	
	
	@Override
	public Login newEntity() {
		
		return new Login();
	}
	

}
