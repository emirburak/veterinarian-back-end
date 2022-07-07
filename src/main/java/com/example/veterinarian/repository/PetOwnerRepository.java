package com.example.veterinarian.repository;

import com.example.veterinarian.model.PetOwner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PetOwnerRepository extends MongoRepository<PetOwner, String > {
}
