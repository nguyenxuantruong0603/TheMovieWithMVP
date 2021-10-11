package com.example.demomvppattern.model;

public class User {
    private String fullName;
    private String userName;
    private String passWord;
    private String address;
    private int age;

    public User(String fullName, String userName, String passWord, String address, int age) {
        this.fullName = fullName;
        this.userName = userName;
        this.passWord = passWord;
        this.address = address;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
