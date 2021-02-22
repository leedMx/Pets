package com.udacity.jdnd.course3.critter.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Entity
public class Pet {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private Long customerId;
    private LocalDate birthdate;
    private String notes;
}
