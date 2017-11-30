package com.appants.adspro.rest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rrallabandi on 11/30/2017.
 */

public class Register {

    @SerializedName("name")
    public String name;

    @SerializedName("mobile_no")
    public String mobile_no;

    @SerializedName("password")
    public String password;

    @SerializedName("dob")
    public String dob;

    @SerializedName("gender")
    public String gender;

    @SerializedName("referral_code")
    public String referral_code;
}
