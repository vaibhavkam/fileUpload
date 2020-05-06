package com.vaibhav.fileUpload.domain;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UserFile {
    
    @NotNull(message = "Bank sheet is missing or doesn't have any record")
    private List<Bank> bankList;
    
    @NotNull(message = "Branch Details sheet is missing or doesn't have any record")
    private List<Branch> branchList;
    
    public List<Bank> getBankList() {
        return bankList;
    }
    
    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }
    
    public List<Branch> getBranchList() {
        return branchList;
    }
    
    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }
}
