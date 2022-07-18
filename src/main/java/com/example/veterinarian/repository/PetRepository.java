package com.example.veterinarian.repository;

import com.example.veterinarian.model.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PetRepository extends MongoRepository<Pet, String> {
}
