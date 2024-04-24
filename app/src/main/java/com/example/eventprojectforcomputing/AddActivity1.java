package com.example.eventprojectforcomputing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class AddActivity1 extends AppCompatActivity {

    EditText eventname1,available1,location1,evaluation1,pictureurl1;
    Button btnadd1,btnback1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        eventname1 = (EditText) findViewById(R.id.txteventname1);
        available1 = (EditText) findViewById(R.id.txtavailable1);
        location1 = (EditText) findViewById(R.id.txtlocation1);
        evaluation1 = (EditText) findViewById(R.id.txtevaluation1);
        pictureurl1 = (EditText) findViewById(R.id.txtpictureurl1);

        btnadd1 = (Button) findViewById(R.id.btnadd1);
        btnback1 = (Button) findViewById(R.id.btnback1);

        btnadd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData1();
                clearAll1();
            }
        });

        btnback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void insertData1(){
        Map<String,Object> map = new HashMap<>();
        map.put("eventname",eventname1.getText().toString());
        map.put("available",available1.getText().toString());
        map.put("location",location1.getText().toString());
        map.put("evaluation",evaluation1.getText().toString());
        map.put("picture",pictureurl1.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Sport").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity1.this,"Data Inserted Successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddActivity1.this,"Error while insertion",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void clearAll1(){
        eventname1.setText("");
        evaluation1.setText("");
        location1.setText("");
        pictureurl1.setText("");
        available1.setText("");
    }
}