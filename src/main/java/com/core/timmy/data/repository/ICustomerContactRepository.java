package com.core.timmy.data.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.timmy.data.model.CustomerContact;


@Repository
public interface ICustomerContactRepository extends JpaRepository<CustomerContact, Integer> { /* solo le tenemos que decir para que tabla y que tipo
de dato es el primarykey de user*/ 
	
	//public Optional<CustomerContact> findByName(String name); //para buscar en base de datos un name
	
	//uso a través de JPA el SELECT * = findByname el FROM = <CustomerContact> y el WHERE =(String name);
	//usar nombres de los atributos completos pero con mayúscula como en el caso de Name y Phone
	
	//Ejmplos de sentencias en JPA: 
	
	public List<CustomerContact> findByName(String name);
	public List<CustomerContact> findByNameOrderByEmailDesc(String name);//el Desc es para que ordene los datos de forma descendente
	public List<CustomerContact> findByNameLike(String name);
	public List<CustomerContact> findByNameAndPhone(String name, String phone);
	public List<CustomerContact> findByIdBetween(Integer start, Integer end); 
	public List<CustomerContact> findByIdLessThan(Integer id); 
	public List<CustomerContact> findByObservationsContaining(String content); 
	
	public List<CustomerContact> findByObservationsIn(Collection<String>searchingWords);
	
	//como hacer joins con JPA
	
	public List<CustomerContact>findByCustomerName(String customerName);

}
