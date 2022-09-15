package com.example.veterinarian.repository;

import com.example.veterinarian.model.Disease;
import com.example.veterinarian.model.enums.EDisease;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface DiseaseRepository extends MongoRepository<Disease,String > {
    Optional<Disease> findByName(EDisease name);
}
