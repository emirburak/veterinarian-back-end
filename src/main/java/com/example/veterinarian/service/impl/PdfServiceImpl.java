package com.example.veterinarian.service.impl;

import com.example.veterinarian.model.Pdf;
import com.example.veterinarian.model.Pet;
import com.example.veterinarian.repository.PdfRepository;
import com.example.veterinarian.repository.PetRepository;
import com.example.veterinarian.service.PdfService;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PdfServiceImpl implements PdfService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    PdfRepository pdfRepository;

    @Autowired
    private GridFsTemplate template;

    @Autowired
    private GridFsOperations operations;

    @Override
    public void savePdf(MultipartFile pdf, String id, String title) throws IOException {
        Pet pet = this.petRepository.findById(id).orElseThrow();

        Pdf pdf2 = new Pdf();
        pdf2.setId(UUID.randomUUID().toString());
        pdf2.setTitle(title);
        pdf2.setPdf(new Binary(BsonBinarySubType.BINARY, pdf.getBytes()));

        if(pet.getPdfId()==null){
            List<String> emptypdfId = new ArrayList<>();
            emptypdfId.add(pdf2.getId());
            pet.setPdfId(emptypdfId);
        }
        else{
            List<String> notEmptyPdfId = pet.getPdfId();
            notEmptyPdfId.add(pdf2.getId());
        }
        this.pdfRepository.save(pdf2);
        this.petRepository.save(pet);
    }

    @Override
    public Pdf getPdf(String id) throws IOException {
        return this.pdfRepository.findById(id).orElseThrow();
    }
}
