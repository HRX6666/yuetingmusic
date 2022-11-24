package com.example.app.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;
import com.example.app.activity.Enter;

public class FristInterface extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.firstinterface);
        Intent intent=new Intent(FristInterface.this, Enter.class);
        intent.putExtra("first_in",true);
        startActivity(intent);
    }
}
