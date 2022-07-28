package com.example.veterinarian.service;

import com.example.veterinarian.model.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

public interface VeterinaryService {

    Veterinary findVetById(String id);

    List<Veterinary> findAllVet();

    void deleteVetById(String id);

    void savePetOwnerToVet(PetOwner petOwner);

    Veterinary findVetByName(String name);

    List<PetOwner> findPetOwnerVetByName(String name);

    void savePetToPetOwner(Pet pet);

    void deletePetOwnerById(String id);

    void saveNoteToPet(List<Note> note, String id) throws Exception;

    void saveDiseaseToPet(Set<String> diseases, String id);
}
