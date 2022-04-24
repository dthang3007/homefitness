package com.example.homefitness;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private Button register;
    private EditText editTextFullName, editTextEmail, editTextPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        register = (Button) findViewById(R.id.button);
        register.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editTextFullName = (EditText) findViewById(R.id.ipFullName);
        editTextEmail = (EditText) findViewById(R.id.ipEmail);
        editTextPassword = (EditText) findViewById(R.id.ipPassword);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                register();
                break;
        }
    }

    private void register() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullName = editTextFullName.getText().toString().trim();
        if (fullName.isEmpty()) {
            editTextFullName.setError("Full name is required");
            editTextFullName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("InValid Email !");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Min password length should be 6 characters!");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User(fullName, email);
                    HashMap<String, List<Day>> exercisePack = new HashMap<String, List<Day>>();
                    exercisePack.put("arm_pack", getListDay());
                    exercisePack.put("leg_pack", getListDay());

                    FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
//                                Toast.makeText(RegisterActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
//                                 startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

                            } else {
                                Toast.makeText(RegisterActivity.this, task.getException().toString(), Toast.LENGTH_LONG).show();
//
                            }
                        }
                    });
                    FirebaseDatabase.getInstance().getReference("Days").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(exercisePack).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                            } else {
                                Toast.makeText(RegisterActivity.this, task.getException().toString(), Toast.LENGTH_LONG).show();
//
                            }
                        }
                    });

                } else {
                    Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            ;
        });
    }

    private List<Day> getListDay() {
        List<Day> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String day = String.valueOf(i + 1);
            list.add(new Day(day,false));
        }

        return list;
    }

}