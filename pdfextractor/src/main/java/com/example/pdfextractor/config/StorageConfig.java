package com.example.pdfextractor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class StorageConfig {

    @Value("${file.storage.dir}")
    private String storageDir;

    @Bean
    public Path storagePath() throws Exception {
        Path path = Paths.get(storageDir);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        return path;
    }
}