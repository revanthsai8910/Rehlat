package com.example.retrofitapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api
{
    @GET("default/dynamodb-writer")
    Call<Response> getDetails();
}
