package com.example.demomvppattern.listener;

public interface IDetailMovieContract {

    interface DetailView {
        void setUpUI();
        void showDiaLog();
    }

    interface Presenter {
        void handleClickWatchMovie();
        void handleClickLikeMovie();
        void handleClickBookMovie();
    }
}
