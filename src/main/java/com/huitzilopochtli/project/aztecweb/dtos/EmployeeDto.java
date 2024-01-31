package com.huitzilopochtli.project.aztecweb.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {

    private Long id;
    private Integer employeeNumber;
    private String laborUnion;
    private LocalDate startDate;
    private LocalDate finishDate;
    private String nss;
    private String statusJob;
    private PersonDto person;
}
