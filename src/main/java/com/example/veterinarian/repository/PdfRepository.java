package com.example.veterinarian.repository;

import com.example.veterinarian.model.Pdf;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfRepository extends MongoRepository<Pdf,String > {
}
