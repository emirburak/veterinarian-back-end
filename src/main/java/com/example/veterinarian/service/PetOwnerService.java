package com.example.veterinarian.service;

import com.example.veterinarian.model.PetOwner;

import java.util.List;

public interface PetOwnerService {

    void savePetOwner(PetOwner petOwner);

    List<PetOwner> getAllPetOwners();

    PetOwner findPetOwnerById(String id);

    void deletePetOwnerById(String id);
}
