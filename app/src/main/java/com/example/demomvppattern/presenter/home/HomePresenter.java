package com.example.demomvppattern.presenter.home;

import android.util.Log;

import com.example.demomvppattern.listener.IHomeContract;
import com.example.demomvppattern.model.movie.ResultTheMovie;
import com.example.demomvppattern.model.movie.TheMovieRepository;

import java.util.Objects;

import retrofit2.Response;

public class HomePresenter implements IHomeContract.Presenter, IHomeContract.APIListener {

    private final IHomeContract.HomeView homeView;
    private final TheMovieRepository theMovieRepo;

    public HomePresenter(IHomeContract.HomeView homeView) {
        this.homeView = homeView;
        homeView.setUpUI();
        theMovieRepo = new TheMovieRepository();
    }

    @Override
    public void getListMovieFromAPI() {
        theMovieRepo.getListMovie(homeView.getKeyTheMovie(), this);
    }

    @Override
    public void onSuccess(Response<ResultTheMovie> theMovieResponse) {
        homeView.setDataToRecycle(Objects.requireNonNull(theMovieResponse.body()).getTheMovies());
        Log.e("onSuccess", theMovieResponse.body().getTheMovies().size() + "");
    }

    @Override
    public void onError(Response<ResultTheMovie> theMovieResponse) {
        Log.e("onError", theMovieResponse.code() + "");
    }

    @Override
    public void onFailure(String message) {
        Log.e("onFailure", message);
    }
}
