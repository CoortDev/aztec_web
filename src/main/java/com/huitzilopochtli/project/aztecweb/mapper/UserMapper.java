package com.huitzilopochtli.project.aztecweb.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.huitzilopochtli.project.aztecweb.dtos.UserDto;
import com.huitzilopochtli.project.aztecweb.entities.UserEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {RoleMapper.class, PersonMapper.class})
public interface UserMapper {

    UserDto toUserDto(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity toUserEntity(UserDto userDto);

    List<UserDto> toUserDtoList(List<UserEntity> userEntitiesList);

    List<UserEntity> toUserEntityList(List<UserDto> userEntitiesList);

}
