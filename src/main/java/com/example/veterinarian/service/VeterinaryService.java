package com.example.veterinarian.service;

import com.example.veterinarian.model.Veterinary;

import java.math.BigInteger;
import java.util.List;

public interface VeterinaryService {
    void saveVet(Veterinary veterinary);
    Veterinary findVetById(String id);
    List<Veterinary> findAllVet();
    void deleteVetById(BigInteger id);
}
