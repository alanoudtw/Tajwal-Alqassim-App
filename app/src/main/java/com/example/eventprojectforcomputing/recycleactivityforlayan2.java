package com.example.eventprojectforcomputing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class recycleactivityforlayan2 extends AppCompatActivity {

    RecyclerView recyclerView2;
    MainAdapterLayan1 mainadapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleactivityforlayan2);

        recyclerView2 = (RecyclerView) findViewById(R.id.rv2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MainModelLayan1> options =
                new FirebaseRecyclerOptions.Builder<MainModelLayan1>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Gym"), MainModelLayan1.class)
                        .build();

        mainadapter2 = new MainAdapterLayan1(options);
        recyclerView2.setAdapter(mainadapter2);
    }

    @Override
    protected void onStart(){
        super.onStart();
        mainadapter2.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        mainadapter2.stopListening();
    }
}