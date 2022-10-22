package com.example.veterinarian.service.impl;

import com.example.veterinarian.exceptions.DiseaseException;
import com.example.veterinarian.exceptions.FalseSecureCodeException;
import com.example.veterinarian.model.*;
import com.example.veterinarian.model.enums.EDisease;
import com.example.veterinarian.repository.DiseaseRepository;
import com.example.veterinarian.repository.PetOwnerRepository;
import com.example.veterinarian.repository.PetRepository;
import com.example.veterinarian.repository.VeterinaryRepository;
import com.example.veterinarian.service.VeterinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VetServiceImpl implements VeterinaryService {

    @Autowired
    VeterinaryRepository veterinaryRepository;

    @Autowired
    PetOwnerRepository petOwnerRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    DiseaseRepository diseaseRepository;

    @Override
    public Veterinary findVetById(String id) {
        return this.veterinaryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Veterinary> findAllVet() {
        return this.veterinaryRepository.findAll();
    }

    @Override
    public void deleteVetById(String id) {
        Veterinary veterinary = this.veterinaryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        List<String> petOwnerIds = veterinary.getPetOwnerId();

        for (int i = 0; i < petOwnerIds.size(); i++) {
            PetOwner petOwner = this.petOwnerRepository.findById(petOwnerIds.get(i)).orElseThrow();
            for (int k = 0; k < petOwner.getPetId().size(); k++) {
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
            pet.setSecureCode(pet.getId().substring(32, 36));
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
        PetOwner petOwner = this.petOwnerRepository.findById(id).orElseThrow();
        Veterinary veterinary = this.veterinaryRepository.findById(petOwner.getVeterinaryId()).orElseThrow(EntityNotFoundException::new);

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

    @Override
    public void saveNoteToPet(List<Note> note, String id) throws Exception {
        Pet pet = this.petRepository.findById(id).orElseThrow();
        if (pet.getSecureCode().matches(note.get(0).getSecureCode())) {
            note.get(0).setNoteDate(new Timestamp(System.currentTimeMillis()));
            if (pet.getNote().size() == 0) {
                List<Note> emptyNoteList = new ArrayList<>();
                emptyNoteList.addAll(note);
                pet.setNote(emptyNoteList);
            } else {
                List<Note> notes = pet.getNote().stream().collect(Collectors.toList());
                notes.addAll(note);
                pet.setNote(notes);
            }
            this.petRepository.save(pet);
        } else {
            throw new FalseSecureCodeException("You did not provide the right secure code");
        }
    }

    @Override
    public void saveDiseaseToPet(Set<String> diseases, String id) {
        Pet pet = this.petRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Set<Disease> newDiseases = new HashSet<>();
        if (diseases == null) {
            Disease notImportant = diseaseRepository.findByName(EDisease.DISEASE_BROKE)
                    .orElseThrow(() -> new DiseaseException("Error: Role is not found."));
            newDiseases.add(notImportant);
        } else {
            diseases.forEach(disease -> {
                if ("broke".equals(disease)) {
                    Disease disease1 = this.diseaseRepository.findByName(EDisease.DISEASE_BROKE)
                            .orElseThrow(() -> new DiseaseException("Error: Disease is not found."+disease));
                    newDiseases.add(disease1);
                }
                if ("hurt".equals(disease)) {
                    Disease disease1 = this.diseaseRepository.findByName(EDisease.DISEASE_HURT)
                            .orElseThrow(() -> new DiseaseException("Error: Disease is not found."+disease));
                    newDiseases.add(disease1);
                }
            });
        }
        if(!newDiseases.isEmpty()){
            pet.setDisease(newDiseases);
            this.petRepository.save(pet);
        }
    }
}
