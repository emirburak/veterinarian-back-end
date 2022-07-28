package com.example.veterinarian.service;


import com.example.veterinarian.model.Pdf;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PdfService {
    void savePdf(MultipartFile pdf, String id, String title) throws IOException;
    Pdf getPdf(String id) throws IOException;
}
