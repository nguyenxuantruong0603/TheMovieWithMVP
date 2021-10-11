package com.example.demomvppattern.presenter.signup;

import com.example.demomvppattern.listener.ISignUpContract;
import com.example.demomvppattern.model.User;

public class SignUpPresenter implements ISignUpContract.Presenter {

    private final ISignUpContract.View view;

    public SignUpPresenter(ISignUpContract.View view) {
        this.view = view;
    }

    @Override
    public void handleSignUp(User user) {
        if (user.getPassWord().length() < 6) {
            view.signUpFail("Mật khẩu phải lớn hơn 6 ký tự");
        } else if (user.getAge() <= 18) {
            view.signUpFail("Số tuổi của bạn phải lớn hơn 18");
        } else {
            view.signUpSuccess("Đăng ký tài khoản thành công");
        }
    }

    @Override
    public void clearForm() {

    }
}
