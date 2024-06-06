package com.example.eventprojectforcomputing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    public EditText loginEmail, loginPassword;
    Button btnLogin;
    TextView registerRedirectText;
    FirebaseAuth firebaseAuth;

    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.btnLogIn);
        registerRedirectText = findViewById(R.id.txtRegister);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(login.this, "قام المستخدم بتسجيل الدخول ", Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(login.this, MainActivity.class);
                    startActivity(I);
                } else {
                    Toast.makeText(login.this, "سجل الدخول للمتابعة", Toast.LENGTH_SHORT).show();
                }
            }
        };

        registerRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(login.this, Signup.class);
                startActivity(I);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = loginEmail.getText().toString();
                String userPassword = loginPassword.getText().toString();

                if (userEmail.isEmpty()) {
                    loginEmail.setError("قم بتقديم بريدك الإلكتروني أولاً");
                    loginEmail.requestFocus();
                } else if (userPassword.isEmpty()) {
                    loginPassword.setError("أدخل كلمة المرور!");
                    loginPassword.requestFocus();
                } else if (userEmail.isEmpty() && userPassword.isEmpty()) {
                    Toast.makeText(login.this, "الحقول فارغة!", Toast.LENGTH_SHORT).show();
                } else if (!(userEmail.isEmpty() && userPassword.isEmpty())) {
                    firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(login.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(login.this, "المعلومات غير صحيحة", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(login.this, MainActivity.class));
                            }
                        }
                    });
                } else {
                    //unexpected errors or when the userEmail and userPassword fields are both empty during the login attempt.
                    Toast.makeText(login.this, "خطأ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}