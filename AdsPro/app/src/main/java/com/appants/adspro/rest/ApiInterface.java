package com.appants.adspro.rest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by rrallabandi on 11/30/2017.
 */

public interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("registration/index")
    Call<String> getSignup(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("registration/getlevelsofuserid")
    Call<String> getLevelStatus(@Body String body);



}