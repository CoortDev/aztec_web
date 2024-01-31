package com.huitzilopochtli.project.aztecweb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class JobsPositionsEmployeesPK {

    @Column(name = "period")
    private String period;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private JobsPositionsEntity position;

}
