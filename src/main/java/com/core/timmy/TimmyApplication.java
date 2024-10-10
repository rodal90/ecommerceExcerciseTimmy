package com.core.timmy;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.core.timmy.data.repository.IRoleRepository;
import com.core.timmy.data.repository.IUserRepository;
import com.core.timmy.data.model.User;
import com.core.timmy.data.model.Role;

@SpringBootApplication
public class TimmyApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TimmyApplication.class, args);
	}

	@Bean
	CommandLineRunner runner( // es como que si estuvieramos usando la consola

			IUserRepository userRepository,
			IRoleRepository roleRepository

	) {

		return args -> { //esta es una funcion anónima en java

			roleRepository.save(new Role("ADMIN")); //abria  que agregar la colleccion en caso de que esto sea bidireccional
			roleRepository.save(new Role("USER"));
			roleRepository.save(new Role("MANAGER"));

			userRepository.save(new User("ana", "ana@gmail.com", "anaPass", "Ana Perez", LocalDate.now().plusMonths(1),
					LocalDate.now().plusMonths(1), true, false, Set.of(roleRepository.findById("ADMIN").get(),roleRepository.findById("USER").get())));
			
			
			
			userRepository.save(new User("luis", "luis@gmail.com", "luisPass", "luis Gomez", LocalDate.now().plusMonths(1),
					LocalDate.now().plusMonths(1), true, false, new HashSet<Role>()));//le hemos dado una coleccion vacia
			userRepository.save(new User("evano", "evano@gmail.com", "evanoPass", "Evano No", LocalDate.now().plusMonths(1),
					LocalDate.now().plusMonths(1), true, false, Set.of(roleRepository.findById("MANAGER").get())));

		};

	}

}
