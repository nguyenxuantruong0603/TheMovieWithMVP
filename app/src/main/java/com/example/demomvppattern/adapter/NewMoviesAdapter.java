package com.example.demomvppattern.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demomvppattern.R;
import com.example.demomvppattern.model.movie.TheMovie;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NewMoviesAdapter extends RecyclerView.Adapter<NewMoviesAdapter.Holder> {

    private final List<TheMovie> theMovieList;

    public NewMoviesAdapter(List<TheMovie> theMovieList) {
        this.theMovieList = theMovieList;
    }

    @NotNull
    @Override
    public Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_movies, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + theMovieList.get(position).getPosterPath()).into(holder.imgPosterMovie);
        holder.tvMovieName.setText(theMovieList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return theMovieList != null ? theMovieList.size() : 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imgPosterMovie;
        TextView tvMovieName;
        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgPosterMovie = itemView.findViewById(R.id.imgPosterMovie);
            tvMovieName = itemView.findViewById(R.id.tvMovieName);
        }
    }
}
