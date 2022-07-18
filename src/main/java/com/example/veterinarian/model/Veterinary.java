package com.example.veterinarian.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "vet")
public class Veterinary {

    @Id
    private String id;

    private String vetName;

    private String password;

    private Date birthDate;

    private Date createdDate;

    private List<String> petOwnerId;
}
