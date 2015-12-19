package com.example.pojo.accountpojo;

/**
 * Created by nadeem on 15-11-2015.
 */
public class PrivilegeVendorAccount {

    private Integer registrationTypeId=3;
    private String registeredCompanyID;
    private String registeredCompanyBrandName;
    private String bankAccountNumber;
    private String ifsc;
    private String panNo;

    public PrivilegeVendorAccount(String registeredCompanyID,
                                  String registeredCompanyBrandName,
                                  String bankAccountNumber,
                                  String ifsc,
                                  String panNo) {
        this.registeredCompanyID = registeredCompanyID;
        this.registeredCompanyBrandName = registeredCompanyBrandName;
        this.bankAccountNumber = bankAccountNumber;
        this.ifsc = ifsc;
        this.panNo = panNo;
    }

    public Integer getRegistrationTypeId() {
        return registrationTypeId;
    }

    public void setRegistrationTypeId(Integer registrationTypeId) {
        this.registrationTypeId = registrationTypeId;
    }

    public String getRegisteredCompanyID() {
        return registeredCompanyID;
    }

    public void setRegisteredCompanyID(String registeredCompanyID) {
        this.registeredCompanyID = registeredCompanyID;
    }

    public String getRegisteredCompanyBrandName() {
        return registeredCompanyBrandName;
    }

    public void setRegisteredCompanyBrandName(String registeredCompanyBrandName) {
        this.registeredCompanyBrandName = registeredCompanyBrandName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }
}
