package com.example.eventprojectforcomputing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView1);
        bottomNavigationView.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.home_bottom) {
            Home();
            return true;
        } else if (itemId == R.id.signout_bottom) {
            signOut();
            return true;
        }

        return false;
    }

    public void testforlayan(View v) {
        Intent i = new Intent(this, recycleactivityforlayan1.class);
        startActivity(i);
        finish();
    }

    public void testforlayan2(View v) {
        Intent i = new Intent(this, recycleactivityforlayan2.class);
        startActivity(i);
        finish();
    }

    public void testforlayan3(View v) {
        Intent i = new Intent(this, recycleactivityforlayan3.class);
        startActivity(i);
        finish();
    }

    public void Home() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(MainActivity.this,login.class);
        startActivity(i);
        finish();
    }
}