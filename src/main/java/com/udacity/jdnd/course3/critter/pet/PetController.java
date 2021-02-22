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
        //This is the original DTO
        //PetDTO(id=0, type=CAT, name=TestPet, ownerId=1, birthDate=null, notes=null)
        //which turns into this Pet to be saved
        //Pet(id=0, name=TestPet, type=CAT, customer=null, birthdate=null, notes=null)
        Pet pet = petService.savePet(petDTO.toPet());
        pet.setCustomer(customerService.getCustomer(petDTO.getOwnerId()));
        //Pet(id=2, name=TestPet, type=CAT, customer=Customer(id=1, name=TestEmployee, phoneNumber=123-456-789, notes=null, pets=[]), birthdate=null, notes=null)
        return new PetDTO().fromPet(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return new PetDTO().fromPet(petService.getPet(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<PetDTO> pets = new LinkedList<>();
        for(Pet pet : petService.getPets())
            pets.add(new PetDTO().fromPet(pet));
        return pets;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetDTO> pets = new LinkedList<>();
        for(Pet pet : petService.getPetsByOwner(ownerId))
            pets.add(new PetDTO().fromPet(pet));
        return pets;
    }
}
