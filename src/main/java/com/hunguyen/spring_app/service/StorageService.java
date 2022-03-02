package com.hunguyen.spring_app.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface StorageService {
    String getStoredFilename(MultipartFile file, String id);

    void store(MultipartFile file, String storedFilename);

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    void delete(String storedFilename) throws IOException;

    void init();
}
