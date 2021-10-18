package com.example.demomvppattern.adapter.viewpager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.demomvppattern.R;
import com.example.demomvppattern.listener.IClickItemTheMovie;
import com.example.demomvppattern.model.movie.TheMovie;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ImageSlideAdapter extends PagerAdapter {
    private final List<TheMovie> theMovieList;
    private IClickItemTheMovie iClickItemTheMovie;

    public ImageSlideAdapter(List<TheMovie> theMovieList, IClickItemTheMovie iClickItemTheMovie) {
        this.theMovieList = theMovieList;
        this.iClickItemTheMovie = iClickItemTheMovie;
    }

    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_image_slide, null);

        ImageView imgBanner = view.findViewById(R.id.imgPoster);
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + theMovieList.get(position).getPosterPath()).into(imgBanner);

        view.setOnClickListener(v -> iClickItemTheMovie.click(theMovieList.get(position)));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
        container.removeView(container);
    }

    @Override
    public int getCount() {
        return theMovieList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view == object;
    }
}
