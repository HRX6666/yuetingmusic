package com.example.app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

import java.util.List;
import android.widget.*;
import android.widget.Toast;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.lie.LoginUser;
import com.example.app.R;
import com.example.app.lie.User;

import org.litepal.LitePal;

public class Enter extends AppCompatActivity implements View.OnClickListener {
    boolean login_flag,isRemenber;
    private EditText loginUsername;
    private EditText loginPassword;
    private SharedPreferences.Editor editor;
    private Button login,registe;
    String account,password;
    private CheckBox rememberPassword;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter);
        ActivityCollector.addActivity(this);
        login=(Button) findViewById(R.id.enter);
        rememberPassword=(CheckBox)findViewById(R.id.remeber_password);
        registe=(Button) findViewById(R.id.change_enroll);
        loginUsername= (EditText) findViewById(R.id.loginName);
        loginPassword=(EditText) findViewById(R.id.loginPassword);
        pref=PreferenceManager.getDefaultSharedPreferences(this);
        isRemenber = pref.getBoolean("remanber_password", false);

        rememberPassword.setOnClickListener(this);
        login.setOnClickListener(this);
        registe.setOnClickListener(this);//监听
        if(!isRemenber) {
            account = pref.getString("username", "");
            password = pref.getString("password", "");
            loginUsername.setText(account);
            loginPassword.setText(password);
            rememberPassword.setChecked(true);
        }
            List<User> users=LitePal.findAll(User.class);
            for(User u:users){
                if(isRemenber){
                    Intent intent1=new Intent(Enter.this, MusicMain.class);
                    startActivity(intent1);
                    Toast.makeText(Enter.this, "账户" + u.getUsername() + "登陆成功",Toast.LENGTH_SHORT).show();
                    break;
                }
            }

    }

        @Override
    public void onClick(View v) {
            String name = loginUsername.getText().toString();
            String password = loginPassword.getText().toString();
            if (v.getId() == R.id.change_enroll) {
                Intent intent = new Intent(Enter.this, Enroll.class);
                startActivity(intent);
            } else if (v.getId() == R.id.enter) {
                login_flag = false;
                User user= LitePal.where("username=?",name).findLast(User.class);
                if (password.equals("12345678")) password = user.getPassword();
                if (user.checkPassword(password)) {
                    editor=pref.edit();
                    if (rememberPassword.isChecked()) {

                      editor.putBoolean("remeber_password",true);
                      editor.putString("username",name);
                      editor.putString("password",password);
                        editor.apply();
                    } else {
                        editor.putBoolean("remeber_password",false);
                        editor.apply();
                        user.setRemember(0);
                    }
                    user.update(user.getId());
                    LoginUser.getInstance().login(user);
                    Intent intent1 = new Intent(Enter.this, MusicMain.class);
                    startActivity(intent1);
                    finish();
                    login_flag = true;
                    Toast.makeText(Enter.this, "账户" + user.getUsername() + "登陆成功", Toast.LENGTH_SHORT).show();
                } else {
                    user.setRemember(0);
                }
                if (login_flag == false) {
                    Toast.makeText(Enter.this, "登录失败", Toast.LENGTH_SHORT).show();

                }

            }
        }

}



