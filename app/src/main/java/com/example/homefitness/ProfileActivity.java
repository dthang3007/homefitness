package com.example.homefitness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView nameText, emailText, daysText;

    String email;

    private FirebaseDatabase database;

    private DatabaseReference databaseReference;

    private Button reportButton;
    private Button logoutButton;

    private Button profileButton;
    private Button mealButton;
    private Button exerciseButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        nameText = findViewById(R.id.userName);
        emailText = findViewById((R.id.userEmail));
        daysText = findViewById(R.id.userDays);

        profileButton=findViewById(R.id.profile_btn);
        mealButton=findViewById(R.id.meal_btn);
        exerciseButton=findViewById(R.id.exercise_btn);
        profileButton.setOnClickListener(this);
        mealButton.setOnClickListener(this);
        exerciseButton.setOnClickListener(this);

        reportButton=findViewById(R.id.report_btn);
        logoutButton=findViewById(R.id.logout_btn);
        reportButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);


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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.profile_btn:
                Intent profileIntent = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
            case R.id.meal_btn:
                Intent mealIntent = new Intent(ProfileActivity.this, Meal.class);
                startActivity(mealIntent);
                break;
            case R.id.exercise_btn:
                Intent exerciseIntent = new Intent(ProfileActivity.this, Exercise.class);
                startActivity(exerciseIntent);
                break;
            case R.id.report_btn:
                Intent reportIntent = new Intent(ProfileActivity.this, Reports.class);
                startActivity(reportIntent);
                break;
            case R.id.logout_btn:
                Intent logoutIntent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(logoutIntent);
                break;
        }
    }
}

