package com.udacity.jdnd.course3.critter.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Pet {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long customer;
}
