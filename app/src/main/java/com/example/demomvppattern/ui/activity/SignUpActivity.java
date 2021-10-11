package com.example.demomvppattern.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demomvppattern.R;
import com.example.demomvppattern.listener.ISignUpContract;
import com.example.demomvppattern.model.User;
import com.example.demomvppattern.presenter.signup.SignUpPresenter;

public class SignUpActivity extends AppCompatActivity implements ISignUpContract.View, View.OnClickListener {

    private SignUpPresenter signUpPresenter;
    private EditText edtFullName;
    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtAddress;
    private EditText edtAge;
    private Button btnSignUp;
    private Button btnClearText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initPresenter();
        initView();
        registerListener();
    }

    private void initView() {
        edtFullName = findViewById(R.id.edtFullName);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtAddress = findViewById(R.id.edtAddress);
        edtAge = findViewById(R.id.edtAge);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnClearText = findViewById(R.id.btnClearText);
    }

    private void registerListener() {
        btnClearText.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    private void initPresenter() {
        signUpPresenter = new SignUpPresenter(this);
    }

    @Override
    public void signUpSuccess(String massage) {
        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
    }

    @Override
    public void signUpFail(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignUp:
                signUpAccount();
                break;
            case R.id.btnClearText:
                clearFormSignUp();
                break;
        }
    }

    private void signUpAccount() {
        String fullName = edtFullName.getText().toString();
        String userName = edtUsername.getText().toString();
        String passWord = edtPassword.getText().toString();
        String address = edtAddress.getText().toString();
        String age = edtAge.getText().toString();
        if (fullName.equals("") || userName.equals("") || passWord.equals("") | address.equals("") || age.equals("")) {
            Toast.makeText(this, "Bạn phải điền đẩy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            signUpPresenter.handleSignUp(new User(fullName, userName, passWord, address, Integer.parseInt(age)));
        }
    }

    private void clearFormSignUp() {
        signUpPresenter.clearForm();
    }

}