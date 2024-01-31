package com.huitzilopochtli.project.aztecweb.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.huitzilopochtli.project.aztecweb.dtos.RoleDto;
import com.huitzilopochtli.project.aztecweb.entities.RoleEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {

    RoleDto toRoleDto(RoleEntity roleEntity);

    @InheritInverseConfiguration
    RoleEntity toRoleEntity(RoleDto roleDto);

    List<RoleDto> toRoleDtoList(List<RoleEntity> roleEntities);

    List<RoleEntity> toRoleEntitiesList(List<RoleDto> roleDtos);

}
