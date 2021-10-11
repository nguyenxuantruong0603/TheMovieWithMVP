package com.example.demomvppattern.listener;

public interface IDetailMovieContract {

    interface DetailView {
        void setUpUI();
    }

    interface Presenter {
        void handleClickWatchMovie();
        void handleClickLikeMovie();
        void handleClickBookMovie();
    }
}
