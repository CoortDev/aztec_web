package com.huitzilopochtli.project.aztecweb.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huitzilopochtli.project.aztecweb.dtos.EmployeeDto;
import com.huitzilopochtli.project.aztecweb.dtos.PersonDto;
import com.huitzilopochtli.project.aztecweb.dtos.custom.ListEmployeeDto;
import com.huitzilopochtli.project.aztecweb.entities.EmployeeEntity;
import com.huitzilopochtli.project.aztecweb.mapper.EmployeeMapper;
import com.huitzilopochtli.project.aztecweb.repositories.EmployeeRepository;
import com.huitzilopochtli.project.aztecweb.services.EmployeeService;
import com.huitzilopochtli.project.aztecweb.services.PersonService;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PersonService personService;

    @Override
    public List<ListEmployeeDto> findAll() {
        return employeeMapper.toListEmployeeDtos((List<EmployeeEntity>)employeeRepository.findAll());
    }

    @Override
    @Transactional(readOnly = false)
    public EmployeeDto save(EmployeeDto employee) {
        
        PersonDto newPerson = personService.save(employee.getPerson());

        employee.setPerson(newPerson);

        EmployeeEntity newEmployee = employeeRepository.save(employeeMapper.toEmployeeEntity(employee));

        return employeeMapper.toEmployeeDto(newEmployee);
    }

}
