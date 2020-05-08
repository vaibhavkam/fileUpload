package com.vaibhav.fileUpload.util;

import com.vaibhav.fileUpload.dto.UserFileDTO;

import java.util.ArrayList;
import java.util.List;

public class ProcessFileResult {
    
    private List<String> errors;
    
    private UserFileDTO data;
    
    public UserFileDTO getUserFile() {
        return data;
    }
    
    public void setUserFile(UserFileDTO userFile) {
        this.data = userFile;
    }
    
    public List<String> getErrors() {
        return errors;
    }
    
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
    
    public void addError(String error) {
        if(this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
    }
}
