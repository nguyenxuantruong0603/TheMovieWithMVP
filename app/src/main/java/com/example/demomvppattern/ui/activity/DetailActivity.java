package com.example.demomvppattern.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demomvppattern.R;
import com.example.demomvppattern.listener.IDetailMovieContract;
import com.example.demomvppattern.presenter.detailmovie.DetailMoviePresenter;

public class DetailActivity extends AppCompatActivity implements IDetailMovieContract.DetailView, View.OnClickListener {
    private DetailMoviePresenter detailMoviePresenter;
    private TextView tvMovieName;
    private ImageView imgBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailMoviePresenter = new DetailMoviePresenter(this, this);

    }

    @Override
    public void setUpUI() {

        tvMovieName = findViewById(R.id.tvMovieName);
        imgBanner = findViewById(R.id.imgBanner);

        tvMovieName.setText("Venom");
        imgBanner.setImageResource(R.drawable.ic_launcher_background);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    @Override
    public void onClick(View v) {

    }
}