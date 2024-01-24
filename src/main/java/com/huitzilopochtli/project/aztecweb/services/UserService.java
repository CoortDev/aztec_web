package com.huitzilopochtli.project.aztecweb.services;

import java.util.List;

import com.huitzilopochtli.project.aztecweb.entities.UserEntity;

public interface UserService {
    List<UserEntity> findAll();

    UserEntity save(UserEntity user);
}
