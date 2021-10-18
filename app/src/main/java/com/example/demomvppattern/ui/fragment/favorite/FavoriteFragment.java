package com.example.demomvppattern.ui.fragment.favorite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.demomvppattern.R;
import com.example.demomvppattern.adapter.recycleview.NewMoviesAdapter;
import com.example.demomvppattern.adapter.viewpager.ImageSlideAdapter;
import com.example.demomvppattern.listener.IClickItemTheMovie;
import com.example.demomvppattern.listener.IFavouriteContract;
import com.example.demomvppattern.model.movie.TheMovie;
import com.example.demomvppattern.presenter.favourite.FavouritePresenter;
import com.example.demomvppattern.ui.activity.DetailActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import me.relex.circleindicator.CircleIndicator;

public class FavoriteFragment extends Fragment implements IFavouriteContract.HomeView, IClickItemTheMovie {

    private final List<TheMovie> movieList = new ArrayList<>();
    private NewMoviesAdapter newMoviesAdapter;
    private ImageSlideAdapter imageSlideAdapter;
    private ViewPager vpMovieTrending;
    private RecyclerView rcMewMovies;
    private CircleIndicator indicator;
    private CountDownTimer cdtSlideImage;

    @SuppressLint("InflateParams")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(Objects.requireNonNull(container).getContext()).inflate(R.layout.fragment_favorite, null);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vpMovieTrending = view.findViewById(R.id.vpMovieTrending);
        rcMewMovies = view.findViewById(R.id.rcNewMovie);
        indicator = view.findViewById(R.id.indicator);

        FavouritePresenter favouritePresenter = new FavouritePresenter(this);
        favouritePresenter.getListMovieFromAPI();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (imageSlideAdapter != null) {
            imageSlideAdapter.unregisterDataSetObserver(indicator.getDataSetObserver());
        }
    }

    @Override
    public String getKeyTheMovie() {
        return getString(R.string.api_key_movie);
    }

    @Override
    public void setDataToRecycle(List<TheMovie> theMovieList) {

        movieList.clear();
        movieList.addAll(theMovieList);
        if (imageSlideAdapter == null && newMoviesAdapter == null) {

            imageSlideAdapter = new ImageSlideAdapter(theMovieList, this);
            vpMovieTrending.setAdapter(imageSlideAdapter);
            indicator.setViewPager(vpMovieTrending);
            imageSlideAdapter.registerDataSetObserver(indicator.getDataSetObserver());
            autoSlideImage();

            newMoviesAdapter = new NewMoviesAdapter(theMovieList);
            rcMewMovies.setAdapter(newMoviesAdapter);
        } else {
            newMoviesAdapter.notifyDataSetChanged();
            imageSlideAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void click(TheMovie theMovie) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("TheMovie", theMovie);
        startActivity(intent);
    }

    private void autoSlideImage() {
        if (!movieList.isEmpty() || vpMovieTrending != null) {
            final int[] currentItem = {vpMovieTrending.getCurrentItem()};
            int totalItem = movieList.size() - 1;
            cdtSlideImage = new CountDownTimer(2000, 200000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if (currentItem[0] < totalItem) {
                        currentItem[0] += 1;
                        vpMovieTrending.setCurrentItem(currentItem[0]);
                    } else {
                        currentItem[0] = 0;
                        vpMovieTrending.setCurrentItem(0);
                    }
                }

                @Override
                public void onFinish() {
                    cdtSlideImage.start();
                }
            }.start();
        }

    }
}
