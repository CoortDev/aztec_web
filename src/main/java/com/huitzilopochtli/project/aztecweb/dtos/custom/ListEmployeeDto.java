package com.huitzilopochtli.project.aztecweb.dtos.custom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @implNote: This DTO is make for show a list of employees like a table in Frontend.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListEmployeeDto {
    private Long id;
    private String fullName;
    private Integer employeeNumber;
    private String statusJob;
}
