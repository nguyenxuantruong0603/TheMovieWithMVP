package com.example.demomvppattern.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demomvppattern.R;
import com.example.demomvppattern.adapter.recycleview.DetailCastAdapter;
import com.example.demomvppattern.listener.IDetailMovieContract;
import com.example.demomvppattern.model.movie.TheMovie;
import com.example.demomvppattern.presenter.detailmovie.DetailMoviePresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements IDetailMovieContract.DetailView, View.OnClickListener {
    private final List<TheMovie> theMovieList = new ArrayList<>();
    private DetailMoviePresenter detailMoviePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailMoviePresenter = new DetailMoviePresenter(this, this);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setUpUI() {

        if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 29) {
            Window w = getWindow();
            w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else if (Build.VERSION.SDK_INT >= 30) {
            Window window = getWindow();
            window.setStatusBarColor(Color.TRANSPARENT);
            WindowCompat.setDecorFitsSystemWindows(window, false);
        }

        TheMovie theMovie = (TheMovie) getIntent().getSerializableExtra("TheMovie");

        initData(theMovie);

        TextView tvMovieName = findViewById(R.id.tvMovieName);
        TextView tvMovieDescription = findViewById(R.id.tvMovieDescription);
        TextView tvVoteAverage = findViewById(R.id.tvVoteAverage);
        TextView tvMovieTime = findViewById(R.id.tvMovieTime);
        TextView tvMovieNameReal = findViewById(R.id.tvMovieNameReal);
        TextView tvBookNow = findViewById(R.id.tvBookNow);
        RecyclerView rcCast = findViewById(R.id.rcCast);
        ImageView imgBanner = findViewById(R.id.imgBanner);
        ImageView imgBack = findViewById(R.id.imgBack);
        ImageView imgLike = findViewById(R.id.imgLike);
        ImageView imgPlay = findViewById(R.id.imgPlay);

        imgBack.setOnClickListener(this);
        imgLike.setOnClickListener(this);
        imgPlay.setOnClickListener(this);
        tvBookNow.setOnClickListener(this);

        DetailCastAdapter detailCastAdapter = new DetailCastAdapter(theMovieList);
        rcCast.setAdapter(detailCastAdapter);

        tvVoteAverage.setText(theMovie.getVoteAverage() + "");
        tvMovieName.setText(theMovie.getTitle());
        tvMovieNameReal.setText(theMovie.getTitle());
        tvMovieDescription.setText(theMovie.getOverview());
        tvMovieTime.setText(theMovie.getReleaseDate());
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + theMovie.getPosterPath()).into(imgBanner);

    }

    @Override
    public void showDiaLog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Like Movie ?");
        builder.setMessage("Do you want to like this video ?");
        builder.setPositiveButton("OK", (dialog, which) -> {
            Toast.makeText(DetailActivity.this, "Like", Toast.LENGTH_SHORT).show();
            dialog.cancel();
        });

        builder.setNegativeButton("Canel", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.imgLike:
                detailMoviePresenter.handleClickLikeMovie();
                break;
            case R.id.imgPlay:
                detailMoviePresenter.handleClickWatchMovie();
                break;
            case R.id.tvBookNow:
                detailMoviePresenter.handleClickBookMovie();
                break;
        }
    }

    private void initData(TheMovie theMovie) {
        for (int i = 0; i < 10; i++) {
            theMovieList.add(theMovie);
        }
    }
}
