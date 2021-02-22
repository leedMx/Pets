package com.udacity.jdnd.course3.critter.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany
    private Set<Skill> skills = new HashSet<>();
    @ManyToMany
    private Set<Weekday> daysAvailable = new HashSet<>();
}
