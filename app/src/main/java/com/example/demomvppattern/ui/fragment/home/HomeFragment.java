package com.example.demomvppattern.ui.fragment.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demomvppattern.R;
import com.example.demomvppattern.adapter.recycleview.MyMovieAdapter;
import com.example.demomvppattern.listener.IClickItemMyMovie;
import com.example.demomvppattern.listener.IHomeContract;
import com.example.demomvppattern.model.mymovie.MyMovie;
import com.example.demomvppattern.presenter.home.HomePresenter;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment implements IHomeContract.View, IClickItemMyMovie {
    private HomePresenter homePresenter;
    private RecyclerView rcMyMovie;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(Objects.requireNonNull(container).getContext()).inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvAddMovie;
        rcMyMovie=view.findViewById(R.id.rcMyMovie);
        tvAddMovie = view.findViewById(R.id.tvAddMovie);
        tvAddMovie.setOnClickListener(v -> homePresenter.addMovie(new MyMovie("HIHI", "HIHIHI", "HIHIHIHI", true)));

        homePresenter = new HomePresenter(this);
        homePresenter.getAllMyMovieFromRealm();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void setUpDataToRecycle(List<MyMovie> myMovieList) {
        Log.e("data_ne", myMovieList.toString());
        MyMovieAdapter myMovieAdapter = new MyMovieAdapter(myMovieList,this);
        rcMyMovie.setAdapter(myMovieAdapter);
    }

    /* IClickItemMyMovie Interface */
    @Override
    public void clickUpdate(MyMovie myMovie) {
        homePresenter.updateMovie(myMovie.getMovieName(), "", "", false);
    }

    @Override
    public void clickDelete(MyMovie myMovie) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa phim");
        builder.setMessage("Bạn chắc chắn muốn xóa phim này ?");
        builder.setPositiveButton("OK", (dialog, which) -> homePresenter.deleteMovie(myMovie.getMovieName()));
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }
}
