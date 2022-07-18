package com.example.veterinarian.service;

import com.example.veterinarian.model.Pet;
import com.example.veterinarian.model.PetOwner;
import com.example.veterinarian.model.Veterinary;

import java.math.BigInteger;
import java.util.List;

public interface VeterinaryService {
    void saveVet(Veterinary veterinary);

    Veterinary findVetById(String id);

    List<Veterinary> findAllVet();

    void deleteVetById(String id);

    void savePetOwnerToVet(PetOwner petOwner);

    Veterinary findVetByName(String name);

    List<PetOwner> findPetOwnerVetByName(String name);

    void savePetToPetOwner(Pet pet);

    void deletePetOwnerById(String id);
}
