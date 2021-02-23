package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    private final CustomerService customerService;

    public Pet savePet(Pet pet) {
        Pet savedPet = petRepository.save(pet);
        enlistWithCustomer(savedPet);
        return savedPet;
    }

    private void enlistWithCustomer(Pet savedPet) {
        if (savedPet.getCustomer() != null){
            Customer customer = savedPet.getCustomer();
            customer.getPets().add(savedPet);
        }
    }

    public Pet getPet(Long petId) {
        return petRepository.findById(petId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Pet> getPetsByOwner(long ownerId) {
        return new LinkedList<>(petRepository.findAllByCustomerId(ownerId));
    }

    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    public Customer getOwnerByPet(long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(EntityNotFoundException::new);
        return pet.getCustomer();
    }
}
