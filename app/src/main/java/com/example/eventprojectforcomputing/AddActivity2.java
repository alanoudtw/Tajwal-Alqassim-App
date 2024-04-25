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

public class AddActivity2 extends AppCompatActivity {

    EditText eventname2,available2,location2,evaluation2,pictureurl2;
    Button btnadd2,btnback2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);

        eventname2 = (EditText) findViewById(R.id.txteventname2);
        available2 = (EditText) findViewById(R.id.txtavailable2);
        location2 = (EditText) findViewById(R.id.txtlocation2);
        evaluation2 = (EditText) findViewById(R.id.txtevaluation2);
        pictureurl2 = (EditText) findViewById(R.id.txtpictureurl2);

        btnadd2 = (Button) findViewById(R.id.btnadd2);
        btnback2 = (Button) findViewById(R.id.btnback2);

        btnadd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData2();
                clearAll2();
            }
        });

        btnback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });

    }

    private void insertData2(){
        Map<String,Object> map = new HashMap<>();
        map.put("eventname",eventname2.getText().toString());
        map.put("available",available2.getText().toString());
        map.put("location",location2.getText().toString());
        map.put("evaluation",evaluation2.getText().toString());
        map.put("picture",pictureurl2.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Gym").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity2.this,"Data Inserted Successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddActivity2.this,"Error while insertion",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAll2(){
        eventname2.setText("");
        evaluation2.setText("");
        location2.setText("");
        pictureurl2.setText("");
        available2.setText("");
    }
}