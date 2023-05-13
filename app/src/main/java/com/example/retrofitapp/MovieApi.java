package com.example.retrofitapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi
{

    @GET("default/dynamodb-writer")
    Call<Response> getMovies();
}
