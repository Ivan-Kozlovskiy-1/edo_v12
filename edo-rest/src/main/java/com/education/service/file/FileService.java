package com.education.service.file;

import com.education.model.dto.FilePoolDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


public interface FileService {
    FilePoolDto uploadFile(MultipartFile multipartFile);
    void loadFile(UUID uuid);
}
