package com.example.veterinarian.controller;

import com.example.veterinarian.model.*;
import com.example.veterinarian.service.impl.VetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VeterinaryController {

    @Autowired
    VetServiceImpl vetService;

    @PostMapping(value = "/savePetOwnerToVet")
    @PreAuthorize("hasRole('VET')")
    public void savePetOwnerToVet(@RequestBody PetOwner petOwner) {
        this.vetService.savePetOwnerToVet(petOwner);
    }

    @PostMapping(value = "/savePetToPetOwner")
    public void savePetToPetOwner(@RequestBody Pet pet) {
        this.vetService.savePetToPetOwner(pet);
    }

    @PostMapping(value = "/saveNoteToPet/{id}")
    public void saveNoteToPet(@RequestBody List<Note> note, @PathVariable String id) throws Exception {
        this.vetService.saveNoteToPet(note,id);
    }

    @GetMapping(value = "/findVetById/{vetId}")
    public Veterinary findVetById(@PathVariable String vetId) {
        return this.vetService.findVetById(vetId);
    }

    @GetMapping(value = "/findAllVets")
    public List<Veterinary> findAllVet() {
        return this.vetService.findAllVet();
    }

    @PreAuthorize("hasRole('ADMIN')")
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
