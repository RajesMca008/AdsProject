package com.appants.adspro.rest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rrallabandi on 11/30/2017.
 */

public class SignUpResponse {

    @SerializedName("result")
    public String result;

    @SerializedName("success")
    public boolean success;
}
