package com.example.File.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.*;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Set;

@Service
public class FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${file.download-dir}")
    private String downloadDir;

    @Value("${file.user-folder-enabled}")
    private boolean userFolderEnabled;

    private static final Set<String> allowedTypes = Set.of(
            "application/pdf", "image/png", "image/jpeg", "text/plain"
    );

    // Static AES key for demonstration (should be stored securely in production)
    private static final String SECRET_KEY = "1234567890123456";

    public ResponseEntity<?> uploadFile(MultipartFile file, String username) throws IOException {
        String contentType = file.getContentType();

        if (!allowedTypes.contains(contentType)) {
            return ResponseEntity.badRequest().body("Unsupported file type: " + contentType);
        }

        String filename = Paths.get(file.getOriginalFilename()).getFileName().toString();
        Path targetPath = userFolderEnabled
                ? Paths.get(uploadDir, username, filename + ".enc")
                : Paths.get(uploadDir, filename + ".enc");

        Files.createDirectories(targetPath.getParent());

        try (FileOutputStream fos = new FileOutputStream(targetPath.toFile());
             CipherOutputStream cos = new CipherOutputStream(fos, getEncryptCipher())) {
            file.getInputStream().transferTo(cos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Encryption failed: " + e.getMessage());
        }

        return ResponseEntity.ok("Encrypted file uploaded to: " + targetPath.toString());
    }

    public ResponseEntity<Resource> downloadFile(String filename, String username) throws IOException {
        Path filePath = userFolderEnabled
                ? Paths.get(uploadDir, username, filename + ".enc")
                : Paths.get(downloadDir, filename + ".enc");

        if (!Files.exists(filePath)) {
            return ResponseEntity.notFound().build();
        }

        try {
            CipherInputStream cis = new CipherInputStream(Files.newInputStream(filePath), getDecryptCipher());
            InputStreamResource resource = new InputStreamResource(cis);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    private Cipher getEncryptCipher() throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher;
    }

    private Cipher getDecryptCipher() throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher;
    }
}
