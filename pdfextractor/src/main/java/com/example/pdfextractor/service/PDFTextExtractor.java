//package com.example.pdfextractor.service;
//import org.apache.pdfbox.Loader;
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.text.PDFTextStripper;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//public class PDFTextExtractor {
//



//    public String extractText(File pdfFile) throws IOException {
//        try (PDDocument document = PDDocument.load(pdfFile)) {
//            PDFTextStripper stripper = new PDFTextStripper();
//            return stripper.getText(document);
//        }
//    }





//    public String extractText(File pdfFile) throws IOException {
//        try (InputStream inputStream = new FileInputStream(pdfFile);
//             PDDocument document = PDDocument.load(inputStream)) {
//            PDFTextStripper stripper = new PDFTextStripper();
//            return stripper.getText(document);
//        }
//    }




//        public String extractText(File pdfFile) throws IOException {
//            try (InputStream inputStream = new FileInputStream(pdfFile);
//                 PDDocument document = Loader.loadPDF(inputStream)) {  // Use Loader, not PDDocument.load
//                PDFTextStripper stripper = new PDFTextStripper();
//                return stripper.getText(document);
//            }
//        }
//
//        public void saveTextToFile(String text, String outputPath) throws IOException {
//            try (java.io.FileWriter writer = new java.io.FileWriter(outputPath)) {
//                writer.write(text);
//            }
//        }
//    }
//

//    public void saveTextToFile(String text, String outputPath) throws IOException {
//        try (java.io.FileWriter writer = new java.io.FileWriter(outputPath)) {
//            writer.write(text);
//        }
//    }
//}


package com.example.pdfextractor.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class PDFTextExtractor {

    public String extractText(File pdfFile) throws IOException {
        try (InputStream inputStream = new FileInputStream(pdfFile)) {
            byte[] pdfBytes = inputStream.readAllBytes();  // Convert InputStream to byte[]
            PDDocument document = Loader.loadPDF(pdfBytes); // Load using byte[]
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }

    public void saveTextToFile(String text, String outputPath) throws IOException {
        try (java.io.FileWriter writer = new java.io.FileWriter(outputPath)) {
            writer.write(text);
        }
    }
}

