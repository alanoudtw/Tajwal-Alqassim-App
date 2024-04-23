package com.example.eventprojectforcomputing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class recycleactivityforlayan3 extends AppCompatActivity {

    RecyclerView recyclerView3;
    MainAdapterLayan1 mainadapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleactivityforlayan3);

        recyclerView3 = (RecyclerView) findViewById(R.id.rv3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MainModelLayan1> options =
                new FirebaseRecyclerOptions.Builder<MainModelLayan1>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Horse"), MainModelLayan1.class)
                        .build();

        mainadapter3 = new MainAdapterLayan1(options);
        recyclerView3.setAdapter(mainadapter3);
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
}