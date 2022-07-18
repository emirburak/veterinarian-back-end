package com.example.veterinarian.controller;

import com.example.veterinarian.model.Pet;
import com.example.veterinarian.model.PetOwner;
import com.example.veterinarian.model.Veterinary;
import com.example.veterinarian.repository.PetOwnerRepository;
import com.example.veterinarian.service.impl.VetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VeterinaryController {

    @Autowired
    VetServiceImpl vetService;

    @Autowired
    PetOwnerRepository petOwnerRepository;


    @PostMapping(value = "/saveVet")
    public void saveVeterinarian(@RequestBody Veterinary veterinary) {
        this.vetService.saveVet(veterinary);
    }

    @PostMapping(value = "/savePetOwnerToVet")
    public void savePetOwnerToVet(@RequestBody PetOwner petOwner) {
        this.vetService.savePetOwnerToVet(petOwner);
    }

    @PostMapping(value = "/savePetToPetOwner")
    public void savePetToPetOwner(@RequestBody Pet pet) {
        this.vetService.savePetToPetOwner(pet);
    }

    @GetMapping(value = "/findVetById/{vetId}")
    public Veterinary findVetById(@PathVariable String vetId) {
        return this.vetService.findVetById(vetId);
    }

    @GetMapping(value = "/findAllVets")
    public List<Veterinary> findAllVet() {
        return this.vetService.findAllVet();
    }

    @DeleteMapping(value = "/deleteVetById/{id}")
    public void deleteVetById(@PathVariable String id) {
        this.vetService.deleteVetById(id);
    }

    @DeleteMapping(value = "deletePetOwnerById/{id}")
    public void deletePetOwnerById(@PathVariable String id) {
        this.vetService.deletePetOwnerById(id);
    }

    @GetMapping(value = "/findVetByName/{name}")
    public Veterinary findVetByName(@PathVariable String name) {
        return this.vetService.findVetByName(name);
    }

    @GetMapping(value = "/findPetOwnerVetByName/{name}")
    public List<PetOwner> findPetOwnerVetByName(@PathVariable String name) {
        return this.vetService.findPetOwnerVetByName(name);
    }
}
