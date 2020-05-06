package com.vaibhav.fileUpload.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class Branch {
    
    @NotNull(message="Branch name should not be empty")
    private String name;
    
    @NotNull(message="Branch type should not be empty")
    private String type;
    
    @NotNull(message="Branch authority name should not be empty")
    private String authorityName;
    
    @NotNull(message="Branch authority PAN should not be empty")
    private String authorityPAN;
    
    @NotNull(message="Address line 1 should not be empty")
    private String addressLine1;
    
    @NotNull(message="Address line 2 should not be empty")
    private String addressLine2;
    
    @NotNull(message="Address line 3 should not be empty")
    private String addressLine3;
    
    @NotNull(message="District should not be empty")
    private String district;
    
    @NotNull(message="State should not be empty")
    private String state;
    
    @NotNull(message="PinCode should not be empty")
    private String pinCode;
    
    @NotNull(message="PinCode should not be empty")
    private String phone;
    
    @NotNull(message="Email should not be empty")
    @Email(message="Please provide a valid email address")
    private String email;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getAuthorityName() {
        return authorityName;
    }
    
    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }
    
    public String getAuthorityPAN() {
        return authorityPAN;
    }
    
    public void setAuthorityPAN(String authorityPAN) {
        this.authorityPAN = authorityPAN;
    }
    
    public String getAddressLine1() {
        return addressLine1;
    }
    
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    
    public String getAddressLine2() {
        return addressLine2;
    }
    
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    
    public String getAddressLine3() {
        return addressLine3;
    }
    
    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }
    
    public String getDistrict() {
        return district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getPinCode() {
        return pinCode;
    }
    
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Override
    public String toString() {
        return "Branch{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", authorityName='" + authorityName + '\'' +
                ", authorityPAN='" + authorityPAN + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", addressLine3='" + addressLine3 + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
