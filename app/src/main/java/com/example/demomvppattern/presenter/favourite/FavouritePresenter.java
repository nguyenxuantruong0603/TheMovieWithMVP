package com.example.demomvppattern.presenter.favourite;

import android.util.Log;

import com.example.demomvppattern.listener.IFavouriteContract;
import com.example.demomvppattern.model.movie.ResultTheMovie;
import com.example.demomvppattern.model.movie.TheMovieRepository;

import java.util.Objects;

import retrofit2.Response;

public class FavouritePresenter implements IFavouriteContract.Presenter, IFavouriteContract.APIListener {

    private final IFavouriteContract.HomeView homeView;
    private final TheMovieRepository theMovieRepo;

    public FavouritePresenter(IFavouriteContract.HomeView homeView) {
        this.homeView = homeView;
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
