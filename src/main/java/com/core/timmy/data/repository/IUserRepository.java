package com.core.timmy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.timmy.data.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, String> { /* solo le tenemos que decir para que tabla y que tipo
de dato es el primarykey de user*/ 

}
