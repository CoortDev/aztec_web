package com.huitzilopochtli.project.aztecweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huitzilopochtli.project.aztecweb.dtos.PersonDto;
import com.huitzilopochtli.project.aztecweb.services.PersonService;



@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/save_person")
    @PreAuthorize("hasRole('ADMIN', 'USER')")
    public ResponseEntity<PersonDto> save(@RequestBody PersonDto person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(person));
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN', 'USER')")
    public ResponseEntity<List<PersonDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
    }
    
}
