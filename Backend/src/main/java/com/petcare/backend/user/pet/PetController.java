package com.petcare.backend.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetController {

    @Autowired
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/pet")
    private List<Pet> getPets() {
        return petService.getPets();
    }

    @GetMapping("/pet/{id}")
    private Pet getPet(@PathVariable("id") int id) {
        return petService.getPetById(id);
    }

    @DeleteMapping("/pet/{id}")
    private void deletePet(@PathVariable("id") int id) {
        petService.delete(id);
    }

    @PostMapping("/pet")
    private int savePet(@RequestBody Pet pet) {
        petService.saveOrUpdate(pet);
        return pet.getId();
    }

}
