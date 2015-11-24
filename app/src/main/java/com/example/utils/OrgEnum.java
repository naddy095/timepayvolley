package com.example.utils;

/**
 * Created by nadeem on 15-11-2015.
 */
public class OrgEnum {
    public static enum orgType
    {
        GPR("0"),
        Vendor("1"),
        PrivilageVendor("2");

        private String orgTypeCode;

        private orgType(String s) {
            orgTypeCode = s;
        }

        public String getOrgTypeCodeCode() {
            return orgTypeCode;
        }
    }
}
