package com.example.veterinarian.repository;

import com.example.veterinarian.model.PetOwner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetOwnerRepository extends MongoRepository<PetOwner, String > {
    PetOwner findByPetOwnerName(String petOwnerName);
}
