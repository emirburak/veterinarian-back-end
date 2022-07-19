package com.example.veterinarian.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "vet")
public class Veterinary {

    @Id
    private String id;

    @NotBlank @Size(max = 20)
    private String username;

    @NotBlank @Size(max = 120)
    private String password;

    @NotBlank @Size(max = 50)
    @Email
    private String email;

    private String vetName;

    private Date createdDate;

    private List<String> petOwnerId;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    public Veterinary(String username, String email, String encode, String vetName) {
        this.username=username;
        this.email=email;
        this.password=encode;
        this.vetName=vetName;
    }
}
