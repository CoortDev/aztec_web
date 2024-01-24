package com.huitzilopochtli.project.aztecweb.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huitzilopochtli.project.aztecweb.entities.RoleEntity;
import com.huitzilopochtli.project.aztecweb.entities.UserEntity;
import com.huitzilopochtli.project.aztecweb.repositories.RoleRepository;
import com.huitzilopochtli.project.aztecweb.repositories.UserRepository;
import com.huitzilopochtli.project.aztecweb.services.UserService;

@Service
/* @Transactional(readOnly = true) */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> findAll() {
        return (List<UserEntity>) userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public UserEntity save(UserEntity user) {

        Optional<RoleEntity> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        List<RoleEntity> roles = new ArrayList<>();
        optionalRoleUser.ifPresent(roles::add);
        
        if (user.isAdmin()) {
            Optional<RoleEntity> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        
        user.setRoles(roles);

        user.setPass(passwordEncoder.encode(user.getPass()));
        return userRepository.save(user);
    }

}
