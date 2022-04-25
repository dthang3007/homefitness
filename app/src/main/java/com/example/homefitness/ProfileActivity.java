package com.example.homefitness;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameText, emailText, daysText;

    String email;

    private FirebaseDatabase database;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        nameText = findViewById(R.id.userName);
        emailText = findViewById((R.id.userEmail));
        daysText = findViewById(R.id.userDays);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("User");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if(ds.child("email").getValue().equals(email)) {
                        nameText.setText(ds.child("fullName").getValue(String.class));
                        emailText.setText(ds.child(email).getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

