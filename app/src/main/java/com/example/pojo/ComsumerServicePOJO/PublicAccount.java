package com.example.pojo.ComsumerServicePOJO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nadeem on 15-11-2015.
 */
public class PublicAccount {

    private Integer registrationTypeId=1;
    private String cardType;
    private String cardBankName;
    private String nameOfCardholder;
    private String expiryDate;
    private String validFrom;
    private String cardNumber;
    private String fullName;
    private String address;
    private String panNo;
    List<CardDetails> cardDetailsList=new ArrayList<CardDetails>();



    public PublicAccount(String cardType, String cardBankName, String nameOfCardholder, String expiryDate, String validFrom, String cardNumber, String fullName, String address,String panNo,List<CardDetails> cardDetailsList) {
        this.cardType = cardType;
        this.cardBankName = cardBankName;
        this.nameOfCardholder = nameOfCardholder;
        this.expiryDate = expiryDate;
        this.validFrom = validFrom;
        this.cardNumber = cardNumber;
        this.fullName = fullName;
        this.address = address;
        this.panNo = panNo;
        this.cardDetailsList=cardDetailsList;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardBankName() {
        return cardBankName;
    }

    public String getNameOfCardholder() {
        return nameOfCardholder;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setCardBankName(String cardBankName) {
        this.cardBankName = cardBankName;
    }

    public void setNameOfCardholder(String nameOfCardholder) {
        this.nameOfCardholder = nameOfCardholder;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public Integer getRegistrationTypeId() {
        return registrationTypeId;
    }

    public void setRegistrationTypeId(Integer registrationTypeId) {
        this.registrationTypeId = registrationTypeId;
    }

    public List<CardDetails> getCardDetailsList() {
        return cardDetailsList;
    }

    public void setCardDetailsList(List<CardDetails> cardDetailsList) {
        this.cardDetailsList = cardDetailsList;
    }
}
