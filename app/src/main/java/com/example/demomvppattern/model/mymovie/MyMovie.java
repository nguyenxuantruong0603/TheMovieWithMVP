package com.example.demomvppattern.model.mymovie;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MyMovie extends RealmObject {
    @PrimaryKey
    private String movieName;
    private String movieTitle;
    private String movieDescription;
    private Boolean movieVideo;

    public MyMovie(String movieName, String movieTitle, String movieDescription, Boolean movieVideo) {
        this.movieName = movieName;
        this.movieTitle = movieTitle;
        this.movieDescription = movieDescription;
        this.movieVideo = movieVideo;
    }

    public MyMovie() {
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public Boolean getMovieVideo() {
        return movieVideo;
    }

    public void setMovieVideo(Boolean movieVideo) {
        this.movieVideo = movieVideo;
    }

    @Override
    public String toString() {
        return "MyMovie{" +
                "movieName='" + movieName + '\'' +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieDescription='" + movieDescription + '\'' +
                ", movieVideo=" + movieVideo +
                '}';
    }
}


