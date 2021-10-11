package com.example.demomvppattern.listener;

import com.example.demomvppattern.model.User;

public interface ISignUpContract {
    interface View {
        void signUpSuccess(String massage);

        void signUpFail(String error);
    }

    interface Presenter {
        void handleSignUp(User user);

        void clearForm();
    }
}
