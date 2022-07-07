package com.example.veterinarian.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "petOwner")
public class PetOwner {

    @Id
    private String id;

    private String petOwnerName;

    private String password;

    private String address;

    private String veterinaryId;

    private String contactNo;

    private Date createdDate;
}
