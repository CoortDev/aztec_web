package com.huitzilopochtli.project.aztecweb.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "employee_number")
    private Integer employeeNumber;

    @Column(name = "labor_union")
    private String laborUnion;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "finish_date", nullable = true)
    private LocalDate finishDate;

    @Column(name = "nss")
    private String nss;

    @Column(name = "status_job")
    private String statusJob;

    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;
    
    /* @OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private List<JobsPositionsEmployeEntity> positions; */
}
