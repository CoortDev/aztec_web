package com.huitzilopochtli.project.aztecweb.services;

import java.util.List;

import com.huitzilopochtli.project.aztecweb.dtos.PersonDto;

public interface PersonService {

    List<PersonDto> findAll();

    PersonDto save(PersonDto person);
}
