package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
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

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet getPet(Long petId) {
        return petRepository.findById(petId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Pet> getPetsByOwner(long ownerId) {
        return new LinkedList<>(petRepository.findAllByCustomerId(ownerId));
    }
}
