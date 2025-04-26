package com.example.pdfextractor.controller;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.pdfextractor.service.PDFTextExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.Resource;


@Controller
public class FileController {

    @Autowired
    private PDFTextExtractor pdfTextExtractor;

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        try {
            // Save uploaded file temporarily
            Path tempFile = Files.createTempFile("upload-", ".pdf");
            Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

            // Process PDF
            String extractedText = pdfTextExtractor.extractText(tempFile.toFile());

            // Save to text file
            String outputFileName = file.getOriginalFilename().replace(".pdf", ".txt");
            pdfTextExtractor.saveTextToFile(extractedText, "extracted-text/" + outputFileName);

            model.addAttribute("message", "File processed successfully!");
            model.addAttribute("downloadLink", outputFileName);
        } catch (Exception e) {
            model.addAttribute("message", "Error processing file: " + e.getMessage());
        }
        return "result";
    }








    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        Path filePath = Path.of("extracted-text/" + filename);
        try {
            Resource resource = new UrlResource(filePath.toUri());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
