package com.example.veterinarian.controller;

import com.example.veterinarian.model.*;
import com.example.veterinarian.security.Log;
import com.example.veterinarian.service.EmailService;
import com.example.veterinarian.service.impl.VetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.SendFailedException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class VeterinaryController {

    @Autowired
    VetServiceImpl vetService;

    @Autowired
    EmailService emailService;


    @PreAuthorize("hasRole('VET')")
    @PostMapping(value = "/savePetOwnerToVet")
    public void savePetOwnerToVet(@RequestBody PetOwner petOwner) {
        this.vetService.savePetOwnerToVet(petOwner);
    }

    @PreAuthorize("hasRole('VET')")
    @PostMapping(value = "/savePetToPetOwner")
    public void savePetToPetOwner(@RequestBody Pet pet) {
        this.vetService.savePetToPetOwner(pet);
    }

    @PreAuthorize("hasRole('VET')")
    @PostMapping(value = "/saveNoteToPet/{id}")
    @Log
    public void saveNoteToPet(@RequestBody List<Note> note, @PathVariable String id) throws Exception {
        this.vetService.saveNoteToPet(note, id);
    }
    @PreAuthorize("hasRole('VET')")
    @GetMapping(value = "/findVetById/{vetId}")
    public Veterinary findVetById(@PathVariable String vetId) {
        return this.vetService.findVetById(vetId);
    }

    @PreAuthorize("hasRole('VET')")
    @GetMapping(value = "/findAllVets")
    public List<Veterinary> findAllVet() {
        return this.vetService.findAllVet();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/deleteVetById/{id}")
    public void deleteVetById(@PathVariable String id) {
        this.vetService.deleteVetById(id);
    }

    @PreAuthorize("hasRole('VET')")
    @DeleteMapping(value = "deletePetOwnerById/{id}")
    public void deletePetOwnerById(@PathVariable String id) {
        this.vetService.deletePetOwnerById(id);
    }

    @PreAuthorize("hasRole('VET')")
    @GetMapping(value = "/findVetByName/{name}")
    public Veterinary findVetByName(@PathVariable String name) {
        return this.vetService.findVetByName(name);
    }

    @PreAuthorize("hasRole('VET')")
    @GetMapping(value = "/findPetOwnerVetByName/{name}")
    public List<PetOwner> findPetOwnerVetByName(@PathVariable String name) {
        return this.vetService.findPetOwnerVetByName(name);
    }

    @PreAuthorize("hasRole('VET')")
    @PostMapping(value = "/saveDiseaseToPet/{id}")
    public void saveDiseaseToPet(@RequestBody Set<String> disease, @PathVariable String id) {
        this.vetService.saveDiseaseToPet(disease, id);
    }


    @PreAuthorize("hasRole('VET')")
    @PostMapping(value = "/sendEmail")
    public String sendEmail(@RequestParam String to, @RequestParam String message, @RequestParam String subject) throws SendFailedException {
        try {
            this.emailService.sendSimpleEmail(to, subject, message);

        } catch (Exception e) {
            throw new SendFailedException(e.getMessage());
        }
        return ("Mail successfully delivered");
    }
}
