package com.example.veterinarian.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pet")
public class Pet {

    @Id
    private String id;

    private String family;

    private String breed;

    private String petName;

    private Integer age;

    private String gender;

    private String petOwnerName;

    private List<Note> note;

    private String secureCode;

    private List<String> pdfId;

    @DBRef
    private Set<Disease> disease;
}
