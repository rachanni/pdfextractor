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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class PDFTextExtractor {

    private static final Logger logger= LoggerFactory.getLogger(PDFTextExtractor.class);
//    public String extractText(File pdfFile) throws IOException {
//        try (InputStream inputStream = new FileInputStream(pdfFile)) {
//            byte[] pdfBytes = inputStream.readAllBytes();  // Convert InputStream to byte[]
//            PDDocument document = Loader.loadPDF(pdfBytes); // Load using byte[]
//            PDFTextStripper stripper = new PDFTextStripper();
//            return stripper.getText(document);
//        }
//    }

    public String extractText(File pdfFile) throws IOException {
        try (PDDocument document = Loader.loadPDF(pdfFile)) {

//           old one  no longer supported try (PDDocument document=PDDocument.load(pdfFile))

//          new version try (PDDocument document = Loader.loadPDF(pdfFile))

//            check for encrypted pdf

            if(document.isEncrypted()){

                throw new IOException("Encrypted PDFs are not supported ");
            }

//            check if PDF is empty

            if(document.getNumberOfPages()==0){

                throw new IOException("PDF contains no pages");
            }

             PDFTextStripper stripper=new PDFTextStripper();
            String text=stripper.getText(document);

//            Additional validation
            if(text.trim().isEmpty()){

                logger.warn("Extracted text is empty for file: {}",pdfFile.getName());

                throw new IOException("PDF appears to be image based or contain no extracable text.");
            }
           return text;
        }
    }

    public void saveTextToFile(String text, String outputPath) throws IOException {
        try (java.io.FileWriter writer = new java.io.FileWriter(outputPath)) {
            writer.write(text);
        }
    }
}

