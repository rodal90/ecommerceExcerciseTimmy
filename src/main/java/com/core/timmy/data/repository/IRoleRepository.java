package com.core.timmy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.timmy.data.model.Role;

//para que identifique esto como un repository le hacemos la anotación así sabe que es una entity o entidad

@Repository
public interface IRoleRepository extends JpaRepository<Role, String> {

}
