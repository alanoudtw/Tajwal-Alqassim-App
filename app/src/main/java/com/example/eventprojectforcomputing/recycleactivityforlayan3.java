package com.example.eventprojectforcomputing;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventprojectforcomputing.MainAdapterLayan3;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class recycleactivityforlayan3 extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    RecyclerView recyclerView3;
    MainAdapterLayan3 mainadapter3;
    FloatingActionButton floatingActionButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleactivityforlayan3);

        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView1);
        bottomNavigationView.setOnItemSelectedListener(this);

        recyclerView3 = findViewById(R.id.rv3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MainModelLayan1> options =
                new FirebaseRecyclerOptions.Builder<MainModelLayan1>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Horse"), MainModelLayan1.class)
                        .build();

        mainadapter3 = new MainAdapterLayan3(options);
        recyclerView3.setAdapter(mainadapter3);

        floatingActionButton3 = findViewById(R.id.floatingActionButton3);
        floatingActionButton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), AddActivity3.class));
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
    protected void onStart(){
        super.onStart();
        mainadapter3.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        mainadapter3.stopListening();
    }

    public void Home() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(String.valueOf(this));
        startActivity(i);
        finish();
    }
}