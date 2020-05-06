package com.vaibhav.fileUpload.domain;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Bank {
    
    @NotNull(message = "Name should not be null")
    private String name;
    
    @NotNull
    private String accountNumber;
    
    @NotNull
    private Date openingDate;
    
    @NotNull(message = "IFSC should not be null")
    private String IFSCCode;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public Date getOpeningDate() {
        return openingDate;
    }
    
    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }
    
    public String getIFSCCode() {
        return IFSCCode;
    }
    
    public void setIFSCCode(String IFSCCode) {
        this.IFSCCode = IFSCCode;
    }
    
    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", openingDate=" + openingDate +
                ", IFSCCode='" + IFSCCode + '\'' +
                '}';
    }
}
