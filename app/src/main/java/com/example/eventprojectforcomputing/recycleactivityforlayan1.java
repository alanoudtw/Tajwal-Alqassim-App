package com.example.eventprojectforcomputing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class recycleactivityforlayan1 extends AppCompatActivity {

    RecyclerView recyclerView1;
    MainAdapterLayan1 mainadapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleactivityforlayan1);

        recyclerView1 = (RecyclerView) findViewById(R.id.rv1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MainModelLayan1> options =
                new FirebaseRecyclerOptions.Builder<MainModelLayan1>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Sport"), MainModelLayan1.class)
                        .build();

        mainadapter1 = new MainAdapterLayan1(options);
        recyclerView1.setAdapter(mainadapter1);
    }

    @Override
    protected void onStart(){
        super.onStart();
        mainadapter1.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        mainadapter1.stopListening();
    }
}