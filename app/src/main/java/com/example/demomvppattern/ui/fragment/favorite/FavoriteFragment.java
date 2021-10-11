package com.example.demomvppattern.ui.fragment.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demomvppattern.R;
import com.example.demomvppattern.adapter.MovieTrendingAdapter;
import com.example.demomvppattern.adapter.NewMoviesAdapter;
import com.example.demomvppattern.listener.IHomeContract;
import com.example.demomvppattern.model.movie.TheMovie;
import com.example.demomvppattern.presenter.home.HomePresenter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements IHomeContract.HomeView {

    private final List<TheMovie> theMovies = new ArrayList<>();
    private MovieTrendingAdapter movieTrendingAdapter;
    private NewMoviesAdapter newMoviesAdapter;
    private RecyclerView rcMovieTrending;
    private RecyclerView rcMewMovies;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_favorite, null);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcMovieTrending = view.findViewById(R.id.rcMovieTrending);
        rcMewMovies = view.findViewById(R.id.rcNewMovie);

        HomePresenter homePresenter = new HomePresenter(this);
        homePresenter.getListMovieFromAPI();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void setUpUI() {

        movieTrendingAdapter = new MovieTrendingAdapter(theMovies);
        rcMovieTrending.setAdapter(movieTrendingAdapter);

        newMoviesAdapter = new NewMoviesAdapter(theMovies);
        rcMewMovies.setAdapter(newMoviesAdapter);
    }

    @Override
    public String getKeyTheMovie() {
        return getString(R.string.api_key_movie);
    }

    @Override
    public void setDataToRecycle(List<TheMovie> theMovieList) {
        theMovies.clear();
        theMovies.addAll(theMovieList);
        movieTrendingAdapter.notifyDataSetChanged();
        newMoviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

}
