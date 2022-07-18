package com.example.veterinarian.repository;

import com.example.veterinarian.model.Veterinary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinaryRepository extends MongoRepository<Veterinary,String>  {
    Veterinary findByVetName(String vetName);

}
