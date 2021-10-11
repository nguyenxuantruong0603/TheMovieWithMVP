package com.example.demomvppattern.listener;

import com.example.demomvppattern.model.movie.ResultTheMovie;
import com.example.demomvppattern.model.movie.TheMovie;

import java.util.List;

import retrofit2.Response;

public interface IHomeContract {

    interface TheMovieRepo {
        void getListMovie(String key, final APIListener apiListener);
    }

    interface HomeView {

        void setUpUI();

        String getKeyTheMovie();

        void setDataToRecycle(List<TheMovie> theMovieList);

        void showProgressBar();

        void hideProgressBar();
    }

    interface APIListener {
        void onSuccess(Response<ResultTheMovie> theMovieResponse);

        void onError(Response<ResultTheMovie> theMovieResponse);

        void onFailure(String message);
    }

    interface Presenter {
        void getListMovieFromAPI();
    }
}
