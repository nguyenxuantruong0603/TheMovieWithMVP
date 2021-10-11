package com.example.demomvppattern.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demomvppattern.R;
import com.example.demomvppattern.listener.ILoginContract;
import com.example.demomvppattern.presenter.login.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginContract.View, View.OnClickListener {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnSignUp;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPresenter();
        initView();
        registerListener();
    }

    private void initView() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
    }

    private void registerListener(){
        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    private void initPresenter() {
        loginPresenter = new LoginPresenter(this);
    }

    private void login() {
        if (edtUsername.getText().toString().equals("") || edtPassword.getText().toString().equals("")) {
            Toast.makeText(this, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
        } else {
            loginPresenter.handleLogin(edtUsername.getText().toString(), edtPassword.getText().toString());
        }
    }

    @Override
    public void LoginSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
    }

    @Override
    public void LoginFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnSignUp:
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                break;
        }
    }
}