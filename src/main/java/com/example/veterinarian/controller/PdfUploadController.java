package com.example.veterinarian.controller;

import com.example.veterinarian.model.Pdf;
import com.example.veterinarian.service.impl.PdfServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class PdfUploadController {

    @Autowired
    PdfServiceImpl pdfService;

    @PostMapping(value = "/uploadPdfToPet/{id}")
    public void uploadPdfToPet(@RequestParam MultipartFile file, @PathVariable String id, @RequestParam String title) throws IOException {
        this.pdfService.savePdf(file,id,title);
    }

    @GetMapping(value = "/downloadPdf/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException{
        Pdf pdf = this.pdfService.getPdf(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdf.getTitle() + "\"" + ".pdf")
                .body(new ByteArrayResource(pdf.getPdf().getData()));
    }
}
