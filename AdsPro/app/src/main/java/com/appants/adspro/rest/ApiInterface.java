package com.appants.adspro.rest;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by rrallabandi on 11/30/2017.
 */

public interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("registration/index")
    Call<String> getSignup(@Body String body);
}