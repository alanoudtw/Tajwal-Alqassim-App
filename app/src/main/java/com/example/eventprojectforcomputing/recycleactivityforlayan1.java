package com.example.eventprojectforcomputing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class recycleactivityforlayan1 extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    RecyclerView recyclerView1;
    MainAdapterLayan1 mainadapter1;
    FloatingActionButton floatingActionButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleactivityforlayan1);

        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView1);
        bottomNavigationView.setOnItemSelectedListener(this);

        recyclerView1 = findViewById(R.id.rv1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MainModelLayan1> options =
                new FirebaseRecyclerOptions.Builder<MainModelLayan1>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Sport"), MainModelLayan1.class)
                        .build();

        mainadapter1 = new MainAdapterLayan1(options);
        recyclerView1.setAdapter(mainadapter1);

        floatingActionButton1 = findViewById(R.id.floatingActionButton1);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddActivity1.class));
            }
        });
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

    @Override
    protected void onStart() {
        super.onStart();
        mainadapter1.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainadapter1.stopListening();
    }

    public void Home() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(this,login.class);
        startActivity(i);
        finish();
    }
}