package com.udacity.jdnd.course3.critter.entity;

import lombok.Data;
import lombok.ToString;

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
    private LocalDate birthdate;
    private String notes;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @ToString.Exclude
    private Customer customer;

    @ToString.Include
    private String customerName() {
        Customer customer = getCustomer();
        return customer == null ? null : customer.getName();
    }
}
