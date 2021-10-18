package com.example.demomvppattern.adapter.recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demomvppattern.R;
import com.example.demomvppattern.listener.IClickItemMyMovie;
import com.example.demomvppattern.model.mymovie.MyMovie;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyMovieAdapter extends RecyclerView.Adapter<MyMovieAdapter.Holder> {

    private final List<MyMovie> myMovieList;
    private final IClickItemMyMovie iClickItemMyMovie;

    public MyMovieAdapter(List<MyMovie> myMovieList, IClickItemMyMovie iClickItemMyMovie) {
        this.myMovieList = myMovieList;
        this.iClickItemMyMovie = iClickItemMyMovie;
    }

    @NotNull
    @Override
    public Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
        holder.tvMyMovieDecs.setText(myMovieList.get(position).getMovieDescription());
        holder.tvMyMovieName.setText(myMovieList.get(position).getMovieName());
        if (position % 2 == 0) {
            holder.imgBannerMovie.setImageResource(R.drawable.caycoi1);
        } else {
            holder.imgBannerMovie.setImageResource(R.drawable.caycoi2);
        }
    }

    @Override
    public int getItemCount() {
        return myMovieList.isEmpty() ? 0 : myMovieList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView imgBannerMovie;
        TextView tvUpdate;
        TextView tvDelete;
        TextView tvMyMovieDecs;
        TextView tvMyMovieName;

        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);

            imgBannerMovie = itemView.findViewById(R.id.imgBannerMovie);
            tvUpdate = itemView.findViewById(R.id.tvUpdate);
            tvDelete = itemView.findViewById(R.id.tvDelete);
            tvMyMovieDecs = itemView.findViewById(R.id.tvMyMovieDecs);
            tvMyMovieName = itemView.findViewById(R.id.tvMyMovieName);

            tvUpdate.setOnClickListener(v -> iClickItemMyMovie.clickUpdate(myMovieList.get(getAdapterPosition())));
            tvDelete.setOnClickListener(v -> iClickItemMyMovie.clickDelete(myMovieList.get(getAdapterPosition())));
        }
    }
}
