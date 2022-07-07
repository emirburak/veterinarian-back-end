package com.example.veterinarian.controller;

import com.example.veterinarian.model.Veterinary;
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


    @PostMapping(value = "/saveVet")
    public void saveVeterinarian(@RequestBody Veterinary veterinary){
        this.vetService.saveVet(veterinary);
    }

    @GetMapping(value = "/findVetById/{vetId}")
        public Veterinary findVetById(@PathVariable String vetId){
            return this.vetService.findVetById(vetId);
        }

    @GetMapping(value = "/findAllVets")
    public List<Veterinary> findAllVet(){
        return this.vetService.findAllVet();
    }

    @DeleteMapping(value = "/deleteVetById/{id}")
    public void deleteVetById(@PathVariable BigInteger id){
        this.vetService.deleteVetById(id);
    }

}
