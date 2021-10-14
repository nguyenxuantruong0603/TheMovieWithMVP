package com.example.demomvppattern.presenter.detailmovie;

import android.content.Context;
import android.widget.Toast;

import com.example.demomvppattern.listener.IDetailMovieContract;

public class DetailMoviePresenter implements IDetailMovieContract.Presenter {
    private final IDetailMovieContract.DetailView detailView;
    private final Context context;

    public DetailMoviePresenter(IDetailMovieContract.DetailView detailView, Context context) {
        this.detailView = detailView;
        this.context = context;
        detailView.setUpUI();
    }

    @Override
    public void handleClickWatchMovie() {
        Toast.makeText(context, "We can not watch Video", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleClickLikeMovie() {

    }

    @Override
    public void handleClickBookMovie() {
        Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
    }
}
