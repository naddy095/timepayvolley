package com.example.pojo.ComsumerServicePOJO;

/**
 * Created by nadeem on 15-11-2015.
 */

/**
 * User Model to save account details
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nadeem on 15-11-2015.
 */

/**
 * Created by nadeem on 15-11-2015.
 */
public class UserAccountModel implements Parcelable {
    private String email;
    private String mobileNumber;
    private String orgType;
    private PublicAccount publicAccount;
    private VendorAccount vendorAccount;
    private PrivilegeVendorAccount privilegeVendorAccount;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getOrgType() {
        return orgType;
    }

    public PublicAccount getPublicAccount() {
        return publicAccount;
    }

    public void setPublicAccount(PublicAccount publicAccount) {
        this.publicAccount = publicAccount;
    }
    public VendorAccount getVendorAccount() {
        return vendorAccount;
    }

    public void setVendorAccount(VendorAccount vendorAccount) {
        this.vendorAccount = vendorAccount;
    }

    public PrivilegeVendorAccount getPrivilegeVendorAccount() {
        return privilegeVendorAccount;
    }

    public void setPrivilegeVendorAccount(PrivilegeVendorAccount privilegeVendorAccount) {
        this.privilegeVendorAccount = privilegeVendorAccount;
    }

    public UserAccountModel() {

    }

    public UserAccountModel(String email, String mobileNumber, String orgType, PublicAccount publicAccount) {
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.orgType = orgType;
        this.publicAccount = publicAccount;
    }

    protected UserAccountModel(Parcel in) {
        email = in.readString();
        mobileNumber = in.readString();
        orgType = in.readString();
        publicAccount = (PublicAccount) in.readValue(PublicAccount.class.getClassLoader());
        vendorAccount = (VendorAccount) in.readValue(VendorAccount.class.getClassLoader());
        privilegeVendorAccount= (PrivilegeVendorAccount) in.readValue(VendorAccount.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(mobileNumber);
        dest.writeString(orgType);
        dest.writeValue(publicAccount);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserAccountModel> CREATOR = new Parcelable.Creator<UserAccountModel>() {
        @Override
        public UserAccountModel createFromParcel(Parcel in) {
            return new UserAccountModel(in);
        }

        @Override
        public UserAccountModel[] newArray(int size) {
            return new UserAccountModel[size];
        }
    };

}