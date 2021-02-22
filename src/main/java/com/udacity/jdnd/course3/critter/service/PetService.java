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

    public PetDTO savePet(PetDTO petDTO) {
        Pet pet = petRepository.save(petDTO.toPet());
        petDTO.setId(pet.getId());
        return petDTO;
    }

    public PetDTO getPet(Long petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(EntityNotFoundException::new);
        return new PetDTO().fromPet(pet);
    }

    public List<PetDTO> getPetsByOwner(long ownerId) {
        List<PetDTO> pets = new LinkedList<>();
        for (Pet pet : petRepository.findAllByCustomerId(ownerId))
            pets.add(new PetDTO().fromPet(pet));
        return pets;
    }
}
