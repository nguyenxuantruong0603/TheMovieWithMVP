package com.example.demomvppattern.listener;

public interface ILoginContract {

    interface View {
        void LoginSuccess(String message);

        void LoginFail(String message);
    }

    interface Presenter {
        void handleLogin(String username, String password);
    }
}
