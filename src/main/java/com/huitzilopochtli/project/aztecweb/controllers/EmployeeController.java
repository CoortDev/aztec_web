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

import com.huitzilopochtli.project.aztecweb.dtos.EmployeeDto;
import com.huitzilopochtli.project.aztecweb.dtos.custom.ListEmployeeDto;
import com.huitzilopochtli.project.aztecweb.services.EmployeeService;



@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN', 'USER')")
    public ResponseEntity<List<ListEmployeeDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAll());
    }
    
    @PostMapping("/save_employee")
    public ResponseEntity<EmployeeDto> save(@RequestBody EmployeeDto employee) {
        
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(employee));
    }
    

}
