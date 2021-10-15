package com.example.demomvppattern.presenter.home;

import android.util.Log;

import com.example.demomvppattern.listener.IHomeContract;
import com.example.demomvppattern.model.mymovie.MyMovie;
import com.example.demomvppattern.model.mymovie.MyMovieRepository;

import java.util.List;

public class HomePresenter implements IHomeContract.MyMovieListener, IHomeContract.Presenter {
    private final MyMovieRepository myMovieRepository;
    private final IHomeContract.View view;

    public HomePresenter(IHomeContract.View view) {
        myMovieRepository = new MyMovieRepository(this);
        this.view = view;
    }

    /*MyMovieListener Interface*/
    @Override
    public void addDataSuccess(String message) {
        Log.e("addDataSuccess", message);
    }

    @Override
    public void addDataError(String error) {
        Log.e("addDataError", error);
    }

    @Override
    public void updateDataSuccess(String message) {
        Log.e("updateDataSuccess", message);
    }

    @Override
    public void updateDataError(String error) {
        Log.e("updateDataError", error);
    }

    @Override
    public void deleteDataSuccess(String message) {
        Log.e("deleteDataSuccess", message);
    }

    @Override
    public void deleteDataError(String error) {
        Log.e("deleteDataError", error);
    }

    @Override
    public void getAllMovie(List<MyMovie> myMovieList) {
        view.setUpDataToRecycle(myMovieList);
    }

    /*Presenter Interface*/
    @Override
    public void getAllMyMovieFromRealm() {
        myMovieRepository.getListMyMovie();
    }

    @Override
    public void addMovie(MyMovie myMovie) {
        myMovieRepository.addMyMovie(myMovie);
    }

    @Override
    public void updateMovie(String movieName, String title, String desc, Boolean video) {
        myMovieRepository.updateMyMovie(movieName, title, desc, video);
    }

    @Override
    public void deleteMovie(String movieName) {
        myMovieRepository.deleteMyMovie(movieName);
    }
}
