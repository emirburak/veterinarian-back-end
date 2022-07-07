package com.example.veterinarian.service.impl;

import com.example.veterinarian.model.PetOwner;
import com.example.veterinarian.model.Veterinary;
import com.example.veterinarian.repository.PetOwnerRepository;
import com.example.veterinarian.repository.VeterinaryRepository;
import com.example.veterinarian.service.VeterinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class VetServiceImpl implements VeterinaryService {

    @Autowired
    VeterinaryRepository veterinaryRepository;

    @Autowired
    PetOwnerRepository petOwnerRepository;

    @Override
    public void saveVet(Veterinary veterinary){

        veterinary.setId(UUID.randomUUID().toString());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        veterinary.setPassword(passwordEncoder.encode(veterinary.getPassword()));

        veterinary.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        petOwnerRepository.save(veterinary.getPetOwner());

        this.veterinaryRepository.save(veterinary);
    }

    @Override
    public Veterinary findVetById(String id) {
        return this.veterinaryRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Veterinary> findAllVet() {
        return this.veterinaryRepository.findAll();
    }

    @Override
    public void deleteVetById(BigInteger id) {
        this.veterinaryRepository.deleteById(id.toString());
    }
}
