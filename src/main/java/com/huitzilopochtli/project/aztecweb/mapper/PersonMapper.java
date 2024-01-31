package com.huitzilopochtli.project.aztecweb.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import com.huitzilopochtli.project.aztecweb.dtos.PersonDto;
import com.huitzilopochtli.project.aztecweb.entities.PersonEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonMapper {

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "firstName", target = "firstName"),
        @Mapping(source = "lastName", target = "lastName"),
        @Mapping(source = "birthdate", target = "birthdate"),
        @Mapping(source = "phoneNumber", target = "phoneNumber"),
        @Mapping(source = "bloodType", target = "bloodType")
    })
    PersonDto toPersonDto(PersonEntity person);

    @InheritInverseConfiguration
    PersonEntity toPersonEntity(PersonDto person);

    List<PersonDto> toPersonDtoList(List<PersonEntity> personEntityList);

    List<PersonEntity> toPersonEntityList(List<PersonDto> personDtoList);
}
