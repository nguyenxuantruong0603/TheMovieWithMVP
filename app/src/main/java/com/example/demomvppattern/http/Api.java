package com.example.demomvppattern.http;

import com.example.demomvppattern.model.movie.ResultTheMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("/3/movie/popular")
    Call<ResultTheMovie> getPopular(@Query("api_key") String api_key);

    @GET("/3/movie/top_rated")
    Call<ResultTheMovie> getTopRated(@Query("api_key") String api_key);

}
