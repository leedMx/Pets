package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(EntityNotFoundException::new);
        System.out.println(customer);
        return customer;
    }

    public List<Customer> getAllCustomers() {
        System.out.println("=========== Finding customers ===========");
        List<Customer> found = customerRepository.findAll();
        System.out.println(found);
        return new LinkedList<>(found);
    }
}
