package com.example.homefitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView register;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button btnLogin;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        register = (TextView) findViewById(R.id.registerActivity);
        register.setOnClickListener(this);
        editTextEmail = findViewById(R.id.ipEmail);
        editTextPassword = findViewById(R.id.ipPassword);
        btnLogin = findViewById(R.id.button);
        btnLogin.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registerActivity:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.button:
                login();
                break;
        }
    }

    private void login() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required !");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password is required !");
            editTextPassword.requestFocus();
            return;
        }
    progressBar.setVisibility(View.VISIBLE) ;
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                }else{
                    Toast.makeText(LoginActivity.this,"Failed",Toast.LENGTH_LONG);
                }
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }
}