package com.example.eventprojectforcomputing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void testforlayan(View v){
        Intent i = new Intent(this,recycleactivityforlayan1.class);
        startActivity(i);
        finish();

    }
    public void testforlayan2(View v){
        Intent i = new Intent(this,recycleactivityforlayan2.class);
        startActivity(i);
        finish();

    }
    public void testforlayan3(View v){
        Intent i = new Intent(this,recycleactivityforlayan3.class);
        startActivity(i);
        finish();

    }
}