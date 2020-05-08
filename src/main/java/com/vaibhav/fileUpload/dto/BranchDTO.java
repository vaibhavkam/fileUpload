package com.vaibhav.fileUpload.dto;

public class BranchDTO {
    
    private String name;
    
    private String type;
    
    private String authorityName;
    
    private String authorityPAN;
    
    private String addressLine1;
    
    private String addressLine2;
    
    private String addressLine3;
    
    private String district;
    
    private String state;
    
    private String pinCode;
    
    private String phone;
    
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
