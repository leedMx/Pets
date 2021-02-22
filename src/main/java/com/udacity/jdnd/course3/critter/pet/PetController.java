package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;
    private final CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = petDTO.toPet();
        Customer customer = customerService.getCustomer(petDTO.getOwnerId());
        pet.setCustomer(customer);
        Pet savedPet = petService.savePet(pet);
        customer.getPets().add(savedPet);
        System.out.println(customer);
        return new PetDTO().fromPet(savedPet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return new PetDTO().fromPet(petService.getPet(petId));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        List<PetDTO> pets = new LinkedList<>();
        for (Pet pet : petService.getPets())
            pets.add(new PetDTO().fromPet(pet));
        return pets;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetDTO> pets = new LinkedList<>();
        for (Pet pet : petService.getPetsByOwner(ownerId))
            pets.add(new PetDTO().fromPet(pet));
        return pets;
    }
}
