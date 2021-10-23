package com.example.demomvppattern.ui.fragment.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment implements IHomeContract.View, IClickItemMyMovie, View.OnClickListener {
    private final List<MyMovie> movieList = new ArrayList<>();
    private HomePresenter homePresenter;
    private MyMovieAdapter myMovieAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(Objects.requireNonNull(container).getContext()).inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvAddMovie;
        RecyclerView rcMyMovie = view.findViewById(R.id.rcMyMovie);
        tvAddMovie = view.findViewById(R.id.tvAddMovie);

        tvAddMovie.setOnClickListener(this);

        myMovieAdapter = new MyMovieAdapter(movieList, this);
        rcMyMovie.setAdapter(myMovieAdapter);

        homePresenter = new HomePresenter(this);
        homePresenter.getAllMyMovieFromRealm();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void setUpDataToRecycle(List<MyMovie> myMovieList) {
        movieList.clear();
        movieList.addAll(myMovieList);
        myMovieAdapter.notifyDataSetChanged();
    }


    /* IClickItemMyMovie Interface */
    @Override
    public void clickUpdate(MyMovie myMovie) {
        homePresenter.updateMovie(myMovie.getMovieName(), "ối giời ôi", "ngáo ngơ rồi", false);
    }

    @Override
    public void clickDelete(MyMovie myMovie) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Xóa phim");
        builder.setMessage("Bạn chắc chắn muốn xóa phim này ?");
        builder.setPositiveButton("OK", (dialog, which) -> {
            homePresenter.deleteMovie(myMovie.getMovieName());
            homePresenter.getAllMyMovieFromRealm();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvAddMovie) {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            View view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_movie, null);
            builder.setView(view);
            builder.setTitle("Thêm Mới Phim");
            AlertDialog alertDialog = builder.create();
            EditText edtMovieName, edtMovieTitle, edtMovieDescription;
            TextView tvAddMyMovie;

            edtMovieName = view.findViewById(R.id.edtMovieName);
            edtMovieTitle = view.findViewById(R.id.edtMovieTitle);
            edtMovieDescription = view.findViewById(R.id.edtMovieDescription);
            tvAddMyMovie = view.findViewById(R.id.tvAddMyMovie);

            tvAddMyMovie.setOnClickListener(v1 -> {

                if (edtMovieName.getText().toString().equals("") || edtMovieTitle.getText().toString().equals("") || edtMovieDescription.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Bạn cần phải nhập đầy đủ thông tin phim ", Toast.LENGTH_SHORT).show();
                } else {
                    homePresenter.addMovie(new MyMovie(edtMovieName.getText().toString(), edtMovieTitle.getText().toString(), edtMovieDescription.getText().toString(), false));
                    Toast.makeText(getContext(), "Đã thêm phim", Toast.LENGTH_SHORT).show();
                    homePresenter.getAllMyMovieFromRealm();
                    alertDialog.dismiss();
                }
            });

            alertDialog.show();
        }
    }
}
