package com.example.veterinarian.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Size(max = 15)
    private String writer;

    @Size(max = 50)
    private String note;

    private Date noteDate;

    @Transient
    private String secureCode;
}
