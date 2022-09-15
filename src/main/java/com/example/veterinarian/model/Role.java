package com.example.veterinarian.model;

import com.example.veterinarian.model.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "roles")
public class Role {

    public Role(ERole name) {
        this.name = name;
    }

    @Id
    private String id;

    private ERole name;
}
