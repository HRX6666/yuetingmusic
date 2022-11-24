package com.example.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app.R;

public class MainActivity extends AppCompatActivity {
    private Button mEnter;
    private Button mEnroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEnter = findViewById(R.id.enter);
        mEnroll=findViewById(R.id.enroll);
        setListeners();
    }
    private void setListeners() {
        OnClick onClick = new OnClick();
        mEnter.setOnClickListener(onClick);
        mEnroll.setOnClickListener(onClick);
    }
    private class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.enter:
                    intent =new Intent(MainActivity.this, Enter.class);
                    break;
                case R.id.enroll:
                    intent=new Intent(MainActivity.this, Enroll.class);
                    break;
            }
            startActivity(intent);
        }


    }
}