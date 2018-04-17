package com.example.demo.service;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface StorageService {

    String store(MultipartFile file);

    Resource loadfile(String filename);

}
