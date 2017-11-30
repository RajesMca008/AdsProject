package com.appants.adspro.rest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by rrallabandi on 11/30/2017.
 */

public interface ApiInterface {
    @Headers("Content-Type: application/json")
    @POST("/registration/index")
    Call<SignUpResponse> getTopRatedMovies(@Body Register register);

}