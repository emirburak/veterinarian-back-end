package com.example.veterinarian.controller;

import com.example.veterinarian.model.PetOwner;
import com.example.veterinarian.service.impl.PetOwnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PetOwnerController {

    @Autowired
    PetOwnerServiceImpl petOwnerService;

    @PostMapping(value = "/savePetOwner")
    public void saveUser(@RequestBody PetOwner petOwner) {
        this.petOwnerService.savePetOwner(petOwner);
    }

    @GetMapping(value = "/findAllPetOwners")
    public List<PetOwner> getAllUsers() {
        return this.petOwnerService.getAllPetOwners();
    }

    @GetMapping(value = "/findPetOwnerById/{id}")
    public PetOwner findUserById(@PathVariable String id){
        return this.petOwnerService.findPetOwnerById(id);
    }

    @DeleteMapping(value = "/deletePetOwnerById/{id}")
    public void deleteUserById(@PathVariable String id){
        this.petOwnerService.deletePetOwnerById(id);
    }
}
