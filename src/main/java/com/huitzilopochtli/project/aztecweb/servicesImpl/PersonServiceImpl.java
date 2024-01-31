package com.huitzilopochtli.project.aztecweb.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huitzilopochtli.project.aztecweb.dtos.PersonDto;
import com.huitzilopochtli.project.aztecweb.entities.PersonEntity;
import com.huitzilopochtli.project.aztecweb.mapper.PersonMapper;
import com.huitzilopochtli.project.aztecweb.repositories.PersonRepository;
import com.huitzilopochtli.project.aztecweb.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @Override
    public List<PersonDto> findAll() {
        return personMapper.toPersonDtoList((List<PersonEntity>) personRepository.findAll());
    }

    @Override
    public PersonDto save(PersonDto person) {
        PersonEntity newPerson = personRepository.save(personMapper.toPersonEntity(person));
        return personMapper.toPersonDto(newPerson);
    }

}
