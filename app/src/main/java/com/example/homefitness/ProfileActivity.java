package com.example.homefitness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private Button reportButton;
    private Button logoutButton;
    private TextView nameText;
    private TextView emailText;

    private Button profileButton;
    private Button mealButton;
    private Button exerciseButton;

    String name, email;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
//        name = getString(R.string.userName);
//        email = getString(R.string.userEmail);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        profileButton=findViewById(R.id.profile_btn);
        mealButton=findViewById(R.id.meal_btn);
        exerciseButton=findViewById(R.id.exercise_btn);
        profileButton.setOnClickListener(this);
        mealButton.setOnClickListener(this);
        exerciseButton.setOnClickListener(this);

        nameText = (TextView) findViewById(R.id.userName);
        emailText = (TextView) findViewById(R.id.userEmail);

        reportButton=findViewById(R.id.report_btn);
        logoutButton=findViewById(R.id.logout_btn);
        reportButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);

        if (user != null) {
            name = user.getDisplayName();
            email = user.getEmail();
            String uid = user.getUid();
        }

        nameText.setText(name);
        emailText.setText(email);


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

