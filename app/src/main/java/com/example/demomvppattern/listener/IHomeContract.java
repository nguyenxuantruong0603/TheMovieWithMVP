package com.example.demomvppattern.listener;

import com.example.demomvppattern.model.mymovie.MyMovie;

import java.util.List;

public interface IHomeContract {

    interface View {
        void setUpDataToRecycle(List<MyMovie> myMovieList);
    }

    interface MyMovieRepo {
        void getListMyMovie();

        void addMyMovie(MyMovie myMovie);

        void updateMyMovie(String movieName, String title, String desc, Boolean video);

        void deleteMyMovie(String movieName);
    }

    interface MyMovieListener {
        void addDataSuccess(String message);

        void addDataError(String error);

        void updateDataSuccess(String message);

        void updateDataError(String error);

        void deleteDataSuccess(String message);

        void deleteDataError(String error);

        void getAllMovie(List<MyMovie> myMovieList);
    }

    interface Presenter {
        void getAllMyMovieFromRealm();

        void addMovie(MyMovie myMovie);

        void updateMovie(String movieName, String title, String desc, Boolean video);

        void deleteMovie(String movieName);
    }
}
