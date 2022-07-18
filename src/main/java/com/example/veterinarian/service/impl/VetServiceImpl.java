package com.example.veterinarian.service.impl;

import com.example.veterinarian.controller.VeterinaryController;
import com.example.veterinarian.model.Pet;
import com.example.veterinarian.model.PetOwner;
import com.example.veterinarian.model.Veterinary;
import com.example.veterinarian.repository.PetOwnerRepository;
import com.example.veterinarian.repository.PetRepository;
import com.example.veterinarian.repository.VeterinaryRepository;
import com.example.veterinarian.service.VeterinaryService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VetServiceImpl implements VeterinaryService {

    @Autowired
    VeterinaryRepository veterinaryRepository;

    @Autowired
    PetOwnerRepository petOwnerRepository;

    @Autowired
    PetRepository petRepository;

    @Override
    public void saveVet(Veterinary veterinary) {

        veterinary.setId(UUID.randomUUID().toString());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        veterinary.setPassword(passwordEncoder.encode(veterinary.getPassword()));

        veterinary.setCreatedDate(new Timestamp(System.currentTimeMillis()));

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
    public void deleteVetById(String id) {
        Veterinary veterinary = this.veterinaryRepository.findById(id).orElseThrow();
        List<String> petOwnerIds = veterinary.getPetOwnerId();

        for(int i=0;i<petOwnerIds.size();i++){
            PetOwner petOwner = this.petOwnerRepository.findById(petOwnerIds.get(i)).orElseThrow();
            for(int k=0;k<petOwner.getPetId().size();k++){
                List<String> petIds = petOwner.getPetId();
                this.petRepository.deleteById(petIds.get(k));
            }
            this.petOwnerRepository.deleteById(petOwnerIds.get(i));
        }
        this.veterinaryRepository.deleteById(id);
    }

    @Override
    public void savePetOwnerToVet(PetOwner petOwner) {
        Veterinary veterinary = this.veterinaryRepository.findByVetName(petOwner.getVetName());
        if (veterinary != null) {
            petOwner.setId(UUID.randomUUID().toString());
            petOwner.setVeterinaryId(veterinary.getId());

            List<String> eklenecekListe = veterinary.getPetOwnerId();
            if (eklenecekListe == null) {
                List<String> yeniList = new ArrayList<>();
                yeniList.add(petOwner.getId());
                veterinary.setPetOwnerId(yeniList);
            } else {
                eklenecekListe.add(petOwner.getId());
                veterinary.setPetOwnerId(eklenecekListe);
            }

            //   MongoClient mongoClient = MongoClients.create();
            //   MongoDatabase database = mongoClient.getDatabase("veterinarian");
            //   MongoCollection<Document> collection = database.getCollection("vet");
            //  Document item = new Document().append("petOwner",petOwner.getId());
            //  UpdateResult updateResult = collection.updateOne(Filters.eq("_id", veterinary.getId()), Updates.push("petOwner", item));

            this.veterinaryRepository.save(veterinary);
            this.petOwnerRepository.save(petOwner);
        }
    }

    @Override
    public Veterinary findVetByName(String name) {
        return this.veterinaryRepository.findByVetName(name);
    }

    @Override
    public List<PetOwner> findPetOwnerVetByName(String name) {
        Veterinary veterinary = this.veterinaryRepository.findByVetName(name);
        List<PetOwner> petOwnerList = new ArrayList<>();
        for (int i = 0; i < veterinary.getPetOwnerId().size(); i++) {
            PetOwner petOwner = this.petOwnerRepository.findById(veterinary.getPetOwnerId().get(i)).orElseThrow();
            petOwnerList.add(petOwner);
        }
        return petOwnerList;
    }

    @Override
    public void savePetToPetOwner(Pet pet) {
        PetOwner petOwner = this.petOwnerRepository.findByPetOwnerName(pet.getPetOwnerName());
        if (petOwner != null) {
            pet.setId(UUID.randomUUID().toString());
            List<String> eklenecekListe = petOwner.getPetId();
            if (eklenecekListe == null) {
                List<String> yeniList = new ArrayList<>();
                yeniList.add(pet.getId());
                petOwner.setPetId(yeniList);
            } else {
                eklenecekListe.add(pet.getId());
                petOwner.setPetId(eklenecekListe);
            }
            this.petRepository.save(pet);
            this.petOwnerRepository.save(petOwner);
        }
    }

    @Override
    public void deletePetOwnerById(String id) {
        PetOwner petOwner  = this.petOwnerRepository.findById(id).orElseThrow();
        Veterinary veterinary = this.veterinaryRepository.findById(petOwner.getVeterinaryId()).orElseThrow();

        List<String> animalIds = petOwner.getPetId();

        List<String> petOwnerIds = veterinary.getPetOwnerId();
        petOwnerIds.remove(petOwner.getId());
        veterinary.setPetOwnerId(petOwnerIds);

        for (int i = 0; i < animalIds.size(); i++) {
            this.petRepository.deleteById(animalIds.get(i));
        }
        this.petOwnerRepository.deleteById(id);
        this.veterinaryRepository.save(veterinary);
    }
}
