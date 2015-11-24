package com.example.pojo.accountpojo;

/**
 * Created by nadeem on 15-11-2015.
 */
public class PublicAccount {

    private String cardType;
    private String cardBankName;
    private String nameOfCardholder;
    private String expiryDate;
    private String validFrom;
    private String cardNumber;
    private String fullName;
    private String address;

    public PublicAccount(String cardType, String cardBankName, String nameOfCardholder, String expiryDate, String validFrom, String cardNumber, String fullName, String address) {
        this.cardType = cardType;
        this.cardBankName = cardBankName;
        this.nameOfCardholder = nameOfCardholder;
        this.expiryDate = expiryDate;
        this.validFrom = validFrom;
        this.cardNumber = cardNumber;
        this.fullName = fullName;
        this.address = address;
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
}
