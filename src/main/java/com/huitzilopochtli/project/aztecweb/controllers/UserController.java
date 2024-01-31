package com.huitzilopochtli.project.aztecweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huitzilopochtli.project.aztecweb.dtos.UserDto;
import com.huitzilopochtli.project.aztecweb.services.UserService;



@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /** 
     * * Cuando no existe una ruta en un GetMapping significa que toma solo la ruta del RequestMapping */
    @GetMapping
    public List<UserDto> list(){
        return userService.findAll();
    }

    /**
     * *Cuando no existe una ruta en un PostMapping significa que toma solo la ruta del RequestMapping */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN', 'USER')")
    public ResponseEntity<UserDto> create(@RequestBody UserDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto user) {
        user.setAdmin(false);
        System.out.println(user);
        return create(user);
    }

    @PostMapping("new_user")
    public ResponseEntity<UserDto> createNewUser(@RequestBody UserDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
    
    
}
