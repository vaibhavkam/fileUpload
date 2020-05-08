package com.vaibhav.fileUpload.util;

import com.vaibhav.fileUpload.domain.Bank;
import com.vaibhav.fileUpload.domain.Branch;
import com.vaibhav.fileUpload.domain.UserFile;
import com.vaibhav.fileUpload.dto.BankDTO;
import com.vaibhav.fileUpload.dto.BranchDTO;
import com.vaibhav.fileUpload.dto.UserFileDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Validation;
import javax.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author vkamble
 * Service to process excel file
 */
@Service
public class FileProcessor {
    
    private static Logger logger = LoggerFactory.getLogger(FileProcessor.class);
    private static final Validator javaxValidator = Validation.buildDefaultValidatorFactory().getValidator();
    private static final SpringValidatorAdapter validator = new SpringValidatorAdapter(javaxValidator);
    
    private String[] sheetsToRead = {"bankDetails","branchDetails"};
    private String[] bankDetailsSheetColumns = {"bankName","accountNumber","accountOpeningDate","ifscCode"};
    private String[] branchDetailsSheetColumns = {"Name of the Branch","Type of Outlet","Name of Authorised signatory","Pan no. of Authorised Signatory","Address line 1","Address line 2","Address line 3","State","District","Pin code","Phone No","Email ID"};
    
    /**
     * Method to process file.
     * Processing includes - Reading file as excel workbook, validate file data and then save.
     * @param file
     * @throws IOException
     */
    public ProcessFileResult processFile(MultipartFile file) throws IOException {
        ProcessFileResult processFileResult = new ProcessFileResult();
        Workbook workbook = readFile(file);
    
        UserFile userFile = new UserFile();
        Errors errors = validateFile(workbook, userFile);
    
        if(errors.hasErrors()) {
            for (ObjectError objectError : errors.getAllErrors()) {
                logger.error(objectError.getDefaultMessage());
            }
        } else {
            saveFile(file, userFile);
        }
        return getProcessFileResult(userFile, errors);
    }
    
    /**
     * Method to read and convert input file into excel Workbook
     * @return
     * @throws IOException
     */
    private Workbook readFile(MultipartFile file) throws IOException {
        
        Path tempDir = Files.createTempDirectory(null);
    
        File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
    
        file.transferTo(tempFile);
    
        Workbook workbook = WorkbookFactory.create(tempFile);
        
        return workbook;
    }
    
    /**
     * Method to validate sheets from input file
     * @param workbook
     * @param userFile
     * @return
     */
    public Errors validateFile(Workbook workbook, UserFile userFile) {
    
        Errors errors = new BeanPropertyBindingResult(userFile, userFile.getClass().getName());
    
        if(workbook != null && workbook.getNumberOfSheets() == sheetsToRead.length) {
            
            for (String sheetName : sheetsToRead) {
                Sheet sheet = workbook.getSheet(sheetName);
                switch (sheetName) {
                    case "bankDetails":
                        userFile.setBankList(validateBankDetailsSheet(sheet, errors));
                        break;
                    case "branchDetails":
                        userFile.setBranchList(validateBranchDetailsSheet(sheet, errors));
                        break;
                }
            }
        }
        validator.validate(userFile, errors);
        return errors;
    }
    
    /**
     * Method to validate Bank details sheet
     * @param sheet
     * @param errors
     * @return
     */
    private List<Bank> validateBankDetailsSheet(Sheet sheet, Errors errors) {
        if(sheet == null) {
            return null;
        }
        int size = sheet.getLastRowNum() - sheet.getFirstRowNum();

        if(size == 0){
            return null;
        }
    
        //Validate column names in bank details sheet
        Row headerRow = sheet.getRow(0);
        int validColumns = 0;
        List<String> bankDetailsSheetColumnList =Arrays.asList(bankDetailsSheetColumns);
        for(Cell columnName: headerRow) {
            if(bankDetailsSheetColumnList.contains(columnName.getStringCellValue())){
                validColumns++;
            }
        }
        
        if(validColumns!=bankDetailsSheetColumns.length){
            return null;
        }
    
        List<Bank> bankList = new ArrayList<>();
        for(int index=1; index<= sheet.getLastRowNum(); index++) {
            Row row = sheet.getRow(index);
            Bank bank = new Bank();
            Cell bankNameCell = row.getCell(0);
            if(bankNameCell == null){
                bank.setName(null);
            } else {
                bank.setName(bankNameCell.getStringCellValue());
            }
    
            Cell accountNumberCell = row.getCell(1);
            if(accountNumberCell == null){
                bank.setAccountNumber(null);
            } else {
                bank.setAccountNumber(accountNumberCell.getStringCellValue());
            }
    
            Cell openingDateCell = row.getCell(2);
            if(openingDateCell == null || !DateUtil.isCellDateFormatted(openingDateCell)){
                bank.setOpeningDate(null);
            } else {
                bank.setOpeningDate(openingDateCell.getDateCellValue());
            }
    
            Cell ifscCodeCell = row.getCell(3);
            if(ifscCodeCell == null){
                bank.setIFSCCode(null);
            } else {
                bank.setIFSCCode(ifscCodeCell.getStringCellValue());
            }
            validator.validate(bank, errors);
            bankList.add(bank);
        }
        return bankList;
    }
    
    /**
     * Method to validate branch details sheet
     * @param sheet
     * @param errors
     * @return
     */
    private List<Branch> validateBranchDetailsSheet(Sheet sheet, Errors errors) {
        if(sheet == null) {
            return null;
        }
        int size = sheet.getLastRowNum() - sheet.getFirstRowNum();
        
        if(size == 0){
            return null;
        }
    
        //Validate column names in branch details sheet
        Row headerRow = sheet.getRow(0);
        int validColumns = 0;
        List<String> branchDetailsSheetColumnList =Arrays.asList(branchDetailsSheetColumns);
        for(Cell columnName: headerRow) {
            if(branchDetailsSheetColumnList.contains(columnName.getStringCellValue())){
                validColumns++;
            }
        }
    
        if(validColumns!=branchDetailsSheetColumns.length){
            return null;
        }
        
        List<Branch> branchList = new ArrayList<>();
        for(int index=1; index<= sheet.getLastRowNum(); index++) {
            Row row = sheet.getRow(index);
            Branch branch = new Branch();
            Cell branchNameCell = row.getCell(0);
            if(branchNameCell == null){
                branch.setName(null);
            } else {
                branch.setName(branchNameCell.getStringCellValue());
            }
    
            Cell branchTypeCell = row.getCell(1);
            if(branchTypeCell == null){
                branch.setType(null);
            } else {
                branch.setType(branchTypeCell.getStringCellValue());
            }
    
            Cell authorityNameCell = row.getCell(2);
            if(authorityNameCell == null){
                branch.setAuthorityName(null);
            } else {
                branch.setAuthorityName(authorityNameCell.getStringCellValue());
            }
    
            Cell authorityPANCell = row.getCell(3);
            if(authorityPANCell == null){
                branch.setAuthorityPAN(null);
            } else {
                branch.setAuthorityPAN(authorityPANCell.getStringCellValue());
            }
    
            Cell addressLine1Cell = row.getCell(4);
            if(addressLine1Cell == null){
                branch.setAddressLine1(null);
            } else {
                branch.setAddressLine1(addressLine1Cell.getStringCellValue());
            }
    
            Cell addressLine2Cell = row.getCell(5);
            if(addressLine2Cell == null){
                branch.setAddressLine2(null);
            } else {
                branch.setAddressLine2(addressLine2Cell.getStringCellValue());
            }
    
            Cell addressLine3Cell = row.getCell(6);
            if(addressLine3Cell == null){
                branch.setAddressLine3(null);
            } else {
                branch.setAddressLine3(addressLine3Cell.getStringCellValue());
            }
    
            Cell districtCell = row.getCell(7);
            if(districtCell == null){
                branch.setDistrict(null);
            } else {
                branch.setDistrict(districtCell.getStringCellValue());
            }
    
            Cell stateCell = row.getCell(8);
            if(stateCell == null){
                branch.setState(null);
            } else {
                branch.setState(stateCell.getStringCellValue());
            }
    
            Cell pinCodeCell = row.getCell(9);
            if(pinCodeCell == null){
                branch.setPinCode(null);
            } else {
                branch.setPinCode(pinCodeCell.getStringCellValue());
            }
    
            Cell phoneCell = row.getCell(10);
            if(phoneCell == null){
                branch.setPhone(null);
            } else {
                branch.setPhone(phoneCell.getStringCellValue());
            }
    
            Cell emailCell = row.getCell(11);
            if(emailCell == null){
                branch.setEmail(null);
            } else {
                branch.setEmail(emailCell.getStringCellValue());
            }
            validator.validate(branch, errors);
            branchList.add(branch);
        }
        return branchList;
    }
    
    /**
     * Method to save validate file
     * @param file
     * @param userFile
     */
    public void saveFile(MultipartFile file, UserFile userFile){
        showUserFile(userFile);
        
        //Need to implement save file logic
    }
    
    /**
     * DELETE THIS METHOD LATER
     * Helper method to print file data
     * @param userFile
     */
    public void showUserFile(UserFile userFile) {
    
        logger.info("----- Bank Details -----");
        for(Bank bank:userFile.getBankList()){
            logger.info(bank.toString());
        }
    
        logger.info("----- Branch Details -----");
        for(Branch branch:userFile.getBranchList()){
            logger.info(branch.toString());
        }
    }
    
    /**
     * Helper method to generate ProcessFileResult
     * @param userFile
     * @param errors
     * @return
     */
    public ProcessFileResult getProcessFileResult(UserFile userFile, Errors errors) {
        ProcessFileResult processFileResult = new ProcessFileResult();
        if(errors.hasErrors()) {
            for(ObjectError objectError: errors.getAllErrors()) {
                processFileResult.addError(objectError.getDefaultMessage());
            }
        } else {
            UserFileDTO userFileDTO = new UserFileDTO();
            if(userFile.getBankList()!=null && userFile.getBankList().size()>0) {
                for(Bank bank: userFile.getBankList()){
                    BankDTO bankDTO = new BankDTO();
                    BeanUtils.copyProperties(bank, bankDTO);
                    userFileDTO.addBank(bankDTO);
                }
            }
            
            if(userFile.getBranchList()!=null && userFile.getBranchList().size()>0) {
                for(Branch branch: userFile.getBranchList()){
                    BranchDTO branchDTO = new BranchDTO();
                    BeanUtils.copyProperties(branch, branchDTO);
                    userFileDTO.addBranch(branchDTO);
                }
            }
            processFileResult.setUserFile(userFileDTO);
        }
        return processFileResult;
    }
}
