package com.example.demomvppattern.ui.fragment.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.demomvppattern.R;

public class UserFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_user, null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}