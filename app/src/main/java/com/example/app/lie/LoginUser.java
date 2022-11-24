package com.example.app.lie;

import android.app.Application;

public class LoginUser extends Application {
    private static LoginUser loginUser = new LoginUser();
    private static User _user;
    private int id;
    private String username;
    private byte[] portrait;
    private String region;
    private String gender;
    private String brithday;

    public static LoginUser getInstance() {
        return loginUser;
    }

    private User getUser() {
        return _user;
    }

    public void update() {
        if (_user.getId() == this.id) {
            _user.setUsername(this.username);
            _user.setPortrait(this.portrait);
            _user.setGender(this.gender);
            _user.setRegion(this.region);
            _user.setBrithday(this.brithday);
            _user.update(_user.getId());
        }
    }

    public void reinit() {
        loginUser.id = _user.getId();
        loginUser.username = _user.getUsername();
        loginUser.portrait = _user.getPortrait();
        loginUser.region = _user.getRegion();
        loginUser.gender = _user.getGender();
        loginUser.brithday = _user.getBrithday();
    }

    public boolean login(User user) {
        _user = user;
        loginUser.id = _user.getId();
        loginUser.username = _user.getUsername();
        loginUser.portrait = _user.getPortrait();
        loginUser.region = _user.getRegion();
        loginUser.gender = _user.getGender();
        loginUser.brithday = _user.getBrithday();
        return true;
    }

    private static boolean logout() {
        if (loginUser.id == -1) {
            return false;
        }
        loginUser.id = -1;
        loginUser.username = null;
        loginUser.portrait = null;
        loginUser.region = null;
        loginUser.gender = null;
        loginUser.brithday = null;
        return true;
    }

    public String toString() {
        return "LoginUser{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", portrait ='" + portrait + '\'' +
                ", region='" + region + '\'' +
                ", gender='" + gender + '\'' +
                ", brithday='" + brithday + '\'' +
                '}';

    }
}

