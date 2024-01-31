package com.huitzilopochtli.project.aztecweb.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.huitzilopochtli.project.aztecweb.dtos.EmployeeDto;
import com.huitzilopochtli.project.aztecweb.dtos.custom.ListEmployeeDto;
import com.huitzilopochtli.project.aztecweb.entities.EmployeeEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {PersonMapper.class})
public abstract class EmployeeMapper {



    public abstract EmployeeDto toEmployeeDto(EmployeeEntity employeeEntity);

    @InheritInverseConfiguration
    public abstract EmployeeEntity toEmployeeEntity(EmployeeDto employeeDto);

    public abstract List<EmployeeDto> toEmployeeDtoList(List<EmployeeEntity> employeeEntitiesList);

    public abstract List<EmployeeEntity> toEmployeeEntitiesList(List<EmployeeDto> employeeEntitiesList);

    public List<ListEmployeeDto> toListEmployeeDtos(List<EmployeeEntity> list) {
        if (list.isEmpty()) {
            return null;
        }

        List<ListEmployeeDto> newList = new ArrayList<ListEmployeeDto>(list.size());
        for (EmployeeEntity item : list) {
            ListEmployeeDto.ListEmployeeDtoBuilder newEmployee = ListEmployeeDto.builder();
            StringBuilder fullName = new StringBuilder();
            fullName.append(item.getPerson().getFirstName());
            fullName.append(" ");
            fullName.append(item.getPerson().getLastName());
            newEmployee.id(item.getId());
            newEmployee.fullName(fullName.toString());
            newEmployee.employeeNumber(item.getEmployeeNumber());
            newEmployee.statusJob(item.getStatusJob());

            newList.add(newEmployee.build());
        }

        return newList;
    }

}
