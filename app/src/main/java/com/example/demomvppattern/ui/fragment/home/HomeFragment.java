package com.example.demomvppattern.ui.fragment.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.demomvppattern.R;
import com.example.demomvppattern.listener.IHomeContract;
import com.example.demomvppattern.model.mymovie.MyMovie;
import com.example.demomvppattern.presenter.home.HomePresenter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeFragment extends Fragment implements IHomeContract.View {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HomePresenter homePresenter = new HomePresenter(this);
        homePresenter.getAllMyMovieFromRealm();

        TextView tvAdd;
        TextView tvUpdate;
        TextView tvDelete;

        tvAdd = view.findViewById(R.id.tvAdd);
        tvUpdate = view.findViewById(R.id.tvUpdate);
        tvDelete = view.findViewById(R.id.tvDelete);

        tvAdd.setOnClickListener(v -> homePresenter.addMovie(new MyMovie("HIHI", "HIHIHI", "HIHIHIHI", true)));
        tvUpdate.setOnClickListener(v -> homePresenter.updateMovie("HIHI", "HIHIHI UPDATE", "HIHIHI UPDATE", false));
        tvDelete.setOnClickListener(v -> homePresenter.deleteMovie("HIHI"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void setUpDataToRecycle(List<MyMovie> myMovieList) {
        Log.e("data_ne",myMovieList.toString());
    }
}