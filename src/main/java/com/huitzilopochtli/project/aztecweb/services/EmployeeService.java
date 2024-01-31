package com.huitzilopochtli.project.aztecweb.services;

import java.util.List;

import com.huitzilopochtli.project.aztecweb.dtos.EmployeeDto;
import com.huitzilopochtli.project.aztecweb.dtos.custom.ListEmployeeDto;

public interface EmployeeService {
    List<ListEmployeeDto> findAll();

    EmployeeDto save(EmployeeDto employee);
}
