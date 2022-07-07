package com.example.veterinarian.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.Date;

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

    @DocumentReference(lookup="{'_id':?#{#self._id} }")
    private PetOwner petOwner;
}
