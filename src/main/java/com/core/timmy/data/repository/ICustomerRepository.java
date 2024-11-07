package com.core.timmy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.timmy.data.model.Customer;


@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> { /* solo le tenemos que decir para que tabla y que tipo
de dato es el primarykey de user*/ 

}
