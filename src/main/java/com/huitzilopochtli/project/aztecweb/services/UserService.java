package com.huitzilopochtli.project.aztecweb.services;

import java.util.List;

import com.huitzilopochtli.project.aztecweb.dtos.UserDto;

public interface UserService {
    List<UserDto> findAll();

    UserDto save(UserDto user);
}
