package com.core.timmy.data.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.timmy.data.model.Contact;


@Repository
public interface IContactRepository extends JpaRepository<Contact, Long> { /* solo le tenemos que decir para que tabla y que tipo
de dato es el primarykey de user*/ 
	
	//public Optional<CustomerContact> findByName(String name); //para buscar en base de datos un name
	
	//uso a través de JPA el SELECT * = findByname el FROM = <CustomerContact> y el WHERE =(String name);
	//usar nombres de los atributos completos pero con mayúscula como en el caso de Name y Phone
	
	//Ejmplos de sentencias en JPA: 
	
	public List<Contact> findByName(String name);
	public List<Contact> findByNameOrderByEmailDesc(String name);//el Desc es para que ordene los datos de forma descendente
	public List<Contact> findByNameLike(String name);
	public List<Contact> findByNameAndPhone(String name, String phone);
	public List<Contact> findByIdBetween(Long start, Long end); 
	public List<Contact> findByIdLessThan(Long id); 
	public List<Contact> findByObservationsContaining(String content); 
	
	public List<Contact> findByObservationsIn(Collection<String>searchingWords);
	
	//como hacer joins con JPA
	
	public List<Contact>findByPersonOfInterestName(String personOfInterestName);

}
