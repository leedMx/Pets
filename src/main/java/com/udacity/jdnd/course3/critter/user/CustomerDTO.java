package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
@Data
public class CustomerDTO {
    private long id;
    private String name;
    private String phoneNumber;
    private String notes;
    private List<Long> petIds = new LinkedList<>();

    public CustomerDTO fromCustomer(Customer customer) {
        setId(customer.getId());
        setName(customer.getName());
        setPhoneNumber(customer.getPhoneNumber());
        setNotes(customer.getNotes());
        for(Pet pet: customer.getPets())
            getPetIds().add(pet.getId());
        return this;
    }

    public Customer toCustomer() {
        Customer customer = new Customer();
        customer.setId(getId());
        customer.setName(getName());
        customer.setPhoneNumber(getPhoneNumber());
        customer.setNotes(getNotes());
        return customer;
    }
}
