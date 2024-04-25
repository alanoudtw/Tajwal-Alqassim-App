package com.example.eventprojectforcomputing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity3 extends AppCompatActivity {

    EditText eventname3,available3,location3,evaluation3,pictureurl3;
    Button btnadd3,btnback3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add3);

        eventname3 = (EditText) findViewById(R.id.txteventname3);
        available3 = (EditText) findViewById(R.id.txtavailable3);
        location3 = (EditText) findViewById(R.id.txtlocation3);
        evaluation3 = (EditText) findViewById(R.id.txtevaluation3);
        pictureurl3 = (EditText) findViewById(R.id.txtpictureurl3);

        btnadd3 = (Button) findViewById(R.id.btnadd3);
        btnback3 = (Button) findViewById(R.id.btnback3);

        btnadd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData3();
                clearAll3();
            }
        });

        btnback3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class)); }
        });

    }

    private void insertData3(){
        Map<String,Object> map = new HashMap<>();
        map.put("eventname",eventname3.getText().toString());
        map.put("available",available3.getText().toString());
        map.put("location",location3.getText().toString());
        map.put("evaluation",evaluation3.getText().toString());
        map.put("picture",pictureurl3.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Horse").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity3.this,"Data Inserted Successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddActivity3.this,"Error while insertion",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAll3(){
        eventname3.setText("");
        evaluation3.setText("");
        location3.setText("");
        pictureurl3.setText("");
        available3.setText("");
    }
}