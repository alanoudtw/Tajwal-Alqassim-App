package com.example.eventprojectforcomputing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;


public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }
    public void getStart(View V){
        Intent start = new Intent(this,Signup.class);
        startActivity(start);
        finish();
    }
}