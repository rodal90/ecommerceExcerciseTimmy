package com.core.timmy.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.timmy.data.model.Customer;
import com.core.timmy.data.model.Provider;


@Repository
public interface IProviderRepository extends JpaRepository<Provider, Long> { /* solo le tenemos que decir para que tabla y que tipo
de dato es el primarykey de user*/ 
	
	public List<Provider> findAllByOrderByName();

}
