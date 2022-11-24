package com.example.app.lie;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.app.SHA256;

import org.litepal.crud.LitePalSupport;

public class User extends LitePalSupport implements Comparable<User> {

    private static String TAG = "User";
    private int id;
    private Integer remember;
    private byte[] portrait;
    private String region;
    private String gender;
    private String brithday;
    private String username;
    private String password;
    private String password2;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", password='" + password + '\'' +
                ", remember=" + remember +
                ", portrait='" + portrait + '\'' +
                ", region='" + region + '\'' +
                ", gender='" + gender + '\'' +
                ", brithday='" + brithday + '\'' +
                '}';
    }

    public boolean checkPassword(String str) {
        Log.d(TAG, "checkPassword: " + str);
        if (remember.equals(0))
            str = SHA256.md5(str);
        Log.d(TAG, "checkPassword: " + str);
        if (password.equals(str))
            return true;
        else
            return false;
    }

    public int getId() {
        return id;
    }

    public String getRegion() {
        return region;
    }

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }

    public String getBrithday() {
        return brithday;
    }

    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPortrait() {
        return portrait;
    }

    public void setPortrait(byte[] portrait) {
        this.portrait = portrait;
    }

    public Integer getRemember() {
        return remember;
    }

    public void setRemember(Integer remember) {
        this.remember = remember;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            User UserInfo = (User) o;
            return (getId() == UserInfo.getId());
        } else {
            return false;
        }
    }

    public int compareTo(@NonNull User User) {
        return this.getUsername().compareTo(User.getUsername());
    }

}
