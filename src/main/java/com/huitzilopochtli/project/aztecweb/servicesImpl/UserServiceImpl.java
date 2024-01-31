package com.huitzilopochtli.project.aztecweb.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huitzilopochtli.project.aztecweb.dtos.RoleDto;
import com.huitzilopochtli.project.aztecweb.dtos.UserDto;
import com.huitzilopochtli.project.aztecweb.entities.RoleEntity;
import com.huitzilopochtli.project.aztecweb.entities.UserEntity;
import com.huitzilopochtli.project.aztecweb.mapper.RoleMapper;
import com.huitzilopochtli.project.aztecweb.mapper.UserMapper;
import com.huitzilopochtli.project.aztecweb.repositories.RoleRepository;
import com.huitzilopochtli.project.aztecweb.repositories.UserRepository;
import com.huitzilopochtli.project.aztecweb.services.PersonService;
import com.huitzilopochtli.project.aztecweb.services.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PersonService personService;


    @Override
    public List<UserDto> findAll() {
        return userMapper.toUserDtoList((List<UserEntity>) userRepository.findAll());
    }

    @Override
    @Transactional(readOnly = false)
    public UserDto save(UserDto user) {

        Optional<RoleEntity> optionalRoleUser = roleRepository.findByName("USER");
        List<RoleDto> roles = new ArrayList<>();

        if (optionalRoleUser.isPresent()) {
            roles.add(roleMapper.toRoleDto(optionalRoleUser.get()));
        }
        
        if (user.isAdmin()) {
            Optional<RoleEntity> optionalRoleAdmin = roleRepository.findByName("ADMIN");
            if (optionalRoleAdmin.isPresent()) {
                roles.add(roleMapper.toRoleDto(optionalRoleAdmin.get()));
            }
        }
        user.setRoles(roles);
        user.setPass(passwordEncoder.encode(user.getPass()));
        user.setPerson(personService.save(user.getPerson()));
        UserEntity newUser = userRepository.save(userMapper.toUserEntity(user));
        return userMapper.toUserDto(newUser);
    }

}
