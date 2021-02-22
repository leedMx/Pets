package com.udacity.jdnd.course3.critter.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Pet {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    @ManyToOne
    @JoinColumn (name = "customer_id")
    private Customer customer;
    private LocalDate birthdate;
    private String notes;
}
