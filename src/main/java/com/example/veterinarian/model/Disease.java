package com.example.veterinarian.model;

import com.example.veterinarian.model.enums.EDisease;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "disease")
public class Disease {

    public Disease(EDisease name){
        this.name=name;
    }

    @Id
    private String id;

    private EDisease name;

    private Date diseaseDate;

    private String diseaseDesc;
}
