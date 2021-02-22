package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;

    public CustomerDTO save(CustomerDTO dto) {
        Customer customer = customerRepository.save(getCustomer(dto));
        dto.setId(customer.getId());
        return dto;
    }

    private Customer getCustomer(CustomerDTO dto) {
        Customer customer = dto.toCustomer();
        for (Long petId : dto.getPetIds())
            addPet(customer, petId);
        return customer;
    }

    private void addPet(Customer customer, Long petId) {
        Optional<Pet> pet = petRepository.findById(petId);
        pet.ifPresent(value -> customer.getPets().add(value));
    }

    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customers = new LinkedList<>();
        for (Customer customer : customerRepository.findAll())
            customers.add(new CustomerDTO().fromCustomer(customer));
        return customers;
    }
}
