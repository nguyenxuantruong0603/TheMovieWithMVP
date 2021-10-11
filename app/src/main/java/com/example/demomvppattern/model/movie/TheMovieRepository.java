package com.example.demomvppattern.model.movie;

import com.example.demomvppattern.http.Api;
import com.example.demomvppattern.http.RetrofitClient;
import com.example.demomvppattern.listener.IHomeContract;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TheMovieRepository implements IHomeContract.TheMovieRepo {

    @Override
    public void getListMovie(String key, IHomeContract.APIListener apiListener) {
        Api api = RetrofitClient.getInstance().create(Api.class);
        Call<ResultTheMovie> resultTheMovieCall = api.getTopRated(key);
        resultTheMovieCall.enqueue(new Callback<ResultTheMovie>() {
            @Override
            public void onResponse(@NotNull Call<ResultTheMovie> call, @NotNull Response<ResultTheMovie> response) {
                if (response.isSuccessful()) {
                    apiListener.onSuccess(response);
                } else {
                    apiListener.onError(response);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResultTheMovie> call, @NotNull Throwable t) {
                apiListener.onFailure(t.getMessage());
            }
        });
    }
}
