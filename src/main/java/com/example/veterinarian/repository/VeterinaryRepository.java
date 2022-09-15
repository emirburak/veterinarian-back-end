package com.example.veterinarian.repository;

import com.example.veterinarian.model.Veterinary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeterinaryRepository extends MongoRepository<Veterinary, String> {
    Veterinary findByVetName(String vetName);
    Optional<Veterinary> findByUsername(String username);
    Boolean existsByUsername(String vetName);
    Boolean existsByEmail(String email);
}
