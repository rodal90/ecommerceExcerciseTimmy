package com.core.timmy.serviceImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//un método que nos permite hacer la busca. Vamos a recuperar y no nos devuelve un user sino un optional, para ver si el user
	 // exister o no existe. La clase optional es una clase envoltorio, cualquier otra clase la podemos meter aqui.  /*Queremos evitar*/
		/*trabajar con nulos por eso usamos optional para luego preguntar por su contenido.*/
		Optional<User> userOpt = this.findById(username);
		if(userOpt.isEmpty()) {
			//ya esta creada en la libreria de excepciones de spring por eso la podemos usar
			throw new UsernameNotFoundException(username);
		}
		//primero quiero comprobar que no este vacio, y luego si se que no esta vacio ya saco lo que tenga adentro.
		return userOpt.get();
	
	}
	
	//sobre escribe un metodo del interface
	@Override
	public User newEntity() {
		
		return new User();
	}
	

}
