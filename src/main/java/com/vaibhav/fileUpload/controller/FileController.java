package com.vaibhav.fileUpload.controller;

import com.vaibhav.fileUpload.util.FileProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {
    
    @Autowired
    FileProcessor fileProcessor;
    
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception{
        fileProcessor.processFile(file);
        return "success";
    }
}
