package com.huitzilopochtli.project.aztecweb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.huitzilopochtli.project.aztecweb.entities.PersonEntity;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long>{
    
}
