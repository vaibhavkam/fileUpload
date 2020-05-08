package com.vaibhav.fileUpload.dto;

import java.util.ArrayList;
import java.util.List;

public class UserFileDTO {
    
    private List<BankDTO> bankList;
    
    private List<BranchDTO> branchList;
    
    public List<BankDTO> getBankList() {
        return bankList;
    }
    
    public void setBankList(List<BankDTO> bankList) {
        this.bankList = bankList;
    }
    
    public List<BranchDTO> getBranchList() {
        return branchList;
    }
    
    public void setBranchList(List<BranchDTO> branchList) {
        this.branchList = branchList;
    }
    
    public void addBank(BankDTO bank) {
        if(this.bankList == null) {
            bankList = new ArrayList<>();
        }
        this.bankList.add(bank);
    }
    
    public void addBranch(BranchDTO branch) {
        if(this.branchList == null) {
            branchList = new ArrayList<>();
        }
        this.branchList.add(branch);
    }
    
}
