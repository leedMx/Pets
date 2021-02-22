package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Pet;
import lombok.Data;

import java.time.LocalDate;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */
@Data
public class PetDTO {
    private long id;
    private PetType type;
    private String name;
    private long ownerId;
    private LocalDate birthDate;
    private String notes;

    public Pet toPet() {
        Pet pet = new Pet();
        pet.setId(getId());
        pet.setType(String.valueOf(getType()));
        pet.setName(getName());
        pet.setCustomerId(getOwnerId());
        pet.setBirthdate(getBirthDate());
        pet.setNotes(getNotes());
        return pet;
    }

    public PetDTO fromPet(Pet pet) {
        setId(pet.getId());
        setType(PetType.valueOf(pet.getType()));
        setName(pet.getName());
        setOwnerId(pet.getCustomerId());
        setBirthDate(pet.getBirthdate());
        setNotes(pet.getNotes());
        return this;
    }
}
