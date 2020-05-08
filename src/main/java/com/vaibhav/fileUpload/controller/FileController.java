package com.vaibhav.fileUpload.controller;

import com.vaibhav.fileUpload.util.FileProcessor;
import com.vaibhav.fileUpload.util.ProcessFileResult;
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
    public ProcessFileResult upload(@RequestParam("file") MultipartFile file) throws Exception{
        return fileProcessor.processFile(file);
    }
}
