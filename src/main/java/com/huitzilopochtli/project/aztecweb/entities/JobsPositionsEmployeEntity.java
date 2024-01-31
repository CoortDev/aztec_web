package com.huitzilopochtli.project.aztecweb.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rel_jobs_positions_employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobsPositionsEmployeEntity {

    @EmbeddedId
    private JobsPositionsEmployeesPK id;

    @Column(name = "start_date")
    private LocalDate startDate;

}
