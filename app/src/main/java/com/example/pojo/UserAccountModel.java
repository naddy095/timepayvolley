package com.example.pojo;

/**
 * Created by nadeem on 15-11-2015.
 */

/**
 * User Model to save account details
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.example.pojo.accountpojo.PublicAccount;

/**
 * Created by nadeem on 15-11-2015.
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.example.pojo.accountpojo.PublicAccount;

/**
 * Created by nadeem on 15-11-2015.
 */
public class UserAccountModel implements Parcelable {
    private String email;
    private String mobileNumber;
    private String orgType;
    private PublicAccount publicAccount;

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