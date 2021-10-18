package com.example.demomvppattern.listener;

import com.example.demomvppattern.model.mymovie.MyMovie;

public interface IClickItemMyMovie {
    void clickUpdate(MyMovie myMovie);

    void clickDelete(MyMovie myMovie);
}
