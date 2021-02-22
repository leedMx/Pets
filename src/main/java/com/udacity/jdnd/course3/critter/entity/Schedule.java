package com.udacity.jdnd.course3.critter.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    @OneToMany
    private Set<Skill> activities;
    @OneToMany
    private List<Employee> employees;
    @OneToMany
    private List<Pet> pets;
}
