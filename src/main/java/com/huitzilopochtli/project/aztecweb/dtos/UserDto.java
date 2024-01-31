package com.huitzilopochtli.project.aztecweb.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String userName;
    private String pass;
    private PersonDto person;
    private Boolean isActive;
    private List<RoleDto> roles;
    private boolean admin;
}
