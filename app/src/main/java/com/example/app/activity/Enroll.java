package com.example.app.activity;

import android.os.Bundle;
import android.view.View;

import android.widget.Toast;
import android.content.Intent;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;
import com.example.app.SHA256;
import com.example.app.lie.User;

import org.litepal.tablemanager.Connector;


public class Enroll extends AppCompatActivity implements View.OnClickListener {
    private Button register;
    private EditText username,userpassord;

@Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityCollector.addActivity(this);
    setContentView(R.layout.enroll);
    username = (EditText) findViewById(R.id.username1);
    userpassord = (EditText) findViewById(R.id.password);


    register = (Button) findViewById(R.id.go_to_main);
    Connector.getDatabase();
    register.setOnClickListener(this);
}
           public void onClick(View v) {

               if (v.getId() == R.id.go_to_main) {

                   String name = username.getText().toString();
                   String password = userpassord.getText().toString();
                   password = SHA256.md5(password);
                   Toast mToast = Toast.makeText(this, null, Toast.LENGTH_SHORT);


                       User user = new User();
                       user.setUsername(name);
                       user.setPassword(password);
                       user.setRemember(0);
                       user.save();

                           mToast.setText("注册成功");
                           mToast.show();
                           Intent intent = new Intent(Enroll.this, Enter.class);
                           startActivity(intent);


               }
           }



    }

