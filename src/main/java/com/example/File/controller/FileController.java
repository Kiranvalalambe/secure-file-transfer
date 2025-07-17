package com.example.File.controller;

import com.example.File.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam String username) throws IOException {
        return fileService.uploadFile(file, username);
    }

    @GetMapping("/download/{username}/{filename}")
    public ResponseEntity<Resource> download(@PathVariable String username, @PathVariable String filename) throws IOException {
        return fileService.downloadFile(filename, username);
    }
}
