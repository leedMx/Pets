package com.udacity.jdnd.course3.critter.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    @ManyToMany
    private Set<Skill> activities = new HashSet<>();
    @ManyToMany
    private List<Employee> employees = new LinkedList<>();
    @ManyToMany
    private List<Pet> pets = new LinkedList<>();
}
