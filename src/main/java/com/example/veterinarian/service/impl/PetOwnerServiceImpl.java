package com.example.veterinarian.service.impl;

import com.example.veterinarian.model.PetOwner;
import com.example.veterinarian.repository.PetOwnerRepository;
import com.example.veterinarian.service.PetOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class PetOwnerServiceImpl implements PetOwnerService {

    @Autowired
    PetOwnerRepository petOwnerRepository;


    @Override
    public void savePetOwner(PetOwner petOwner) {

        petOwner.setId(UUID.randomUUID().toString());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        petOwner.setPassword(passwordEncoder.encode(petOwner.getPassword()));

        petOwner.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        this.petOwnerRepository.save(petOwner);
    }

    @Override
    public List<PetOwner> getAllPetOwners() {
        return this.petOwnerRepository.findAll();
    }

    @Override
    public PetOwner findPetOwnerById(String id) {
        return this.petOwnerRepository.findById(id).orElseThrow();
    }

    @Override
    public void deletePetOwnerById(String id) {

    }
}
