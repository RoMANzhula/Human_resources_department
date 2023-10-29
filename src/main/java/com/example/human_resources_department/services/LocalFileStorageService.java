package com.example.human_resources_department.services;

import com.example.human_resources_department.excatchers.FileStorageException;
import com.example.human_resources_department.interfaces.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class LocalFileStorageService implements FileStorageService {
    @Value(("${upload.path}"))
    private String uploadPath;

    @Override
    public String storeFile(MultipartFile file) {
        try {
            String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            String uniqueFileName = UUID.randomUUID().toString() + "." + originalFileName;
            Path targetLocation = Paths.get(uploadPath).toAbsolutePath().resolve(uniqueFileName);

            File targetFile = targetLocation.toFile();
            File targetDirectory = targetFile.getParentFile();

            if (!targetDirectory.exists()) {
                targetDirectory.mkdirs();
            }

            file.transferTo(targetFile);

            return uniqueFileName;

        } catch (IOException ex) {
            throw new FileStorageException(
                    "Could not store file " + file.getOriginalFilename() + ". Please try again!", ex
            );
        }

    }
}
