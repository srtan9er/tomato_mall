package com.example.tomatomall.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService {
    String upload(MultipartFile file);
}
