package com.example.demomvppattern.adapter.recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demomvppattern.R;
import com.example.demomvppattern.model.movie.TheMovie;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DetailCastAdapter extends RecyclerView.Adapter<DetailCastAdapter.Holder> {

    private final List<TheMovie> theMovieList;

    public DetailCastAdapter(List<TheMovie> theMovieList) {
        this.theMovieList = theMovieList;
    }

    @NotNull
    @Override
    public Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cast, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + theMovieList.get(position).getBackdropPath()).into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return theMovieList != null ? theMovieList.size() : 0;
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView imgPoster;

        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.imgPoster);

        }
    }
}