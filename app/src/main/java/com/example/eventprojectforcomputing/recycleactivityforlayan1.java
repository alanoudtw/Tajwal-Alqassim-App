package com.example.eventprojectforcomputing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class recycleactivityforlayan1 extends AppCompatActivity {

    RecyclerView recyclerView1;
    MainAdapterLayan1 mainadapter1;
    FloatingActionButton floatingActionButton1;

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

        floatingActionButton1 = (FloatingActionButton)findViewById(R.id.floatingActionButton1);


        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddActivity1.class));
            }
        });
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