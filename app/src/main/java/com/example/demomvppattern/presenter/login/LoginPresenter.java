package com.example.demomvppattern.presenter.login;

import com.example.demomvppattern.listener.ILoginContract;

public class LoginPresenter implements ILoginContract.Presenter {

    private final ILoginContract.View view;

    public LoginPresenter(ILoginContract.View view) {
        this.view = view;
    }

    @Override
    public void handleLogin(String username, String password) {
        if (username.equals("truongnx") && password.equals("123")) {
            view.LoginSuccess("xin chào" + username);
        } else {
            view.LoginFail("Tài khoản hoặc mật khẩu không chính xác ");
        }
    }
}
