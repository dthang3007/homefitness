package com.example.homefitness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Meal extends AppCompatActivity implements View.OnClickListener {
    private Button Day1;

    private Button profileButton;
    private Button mealButton;
    private Button exerciseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        Day1 = (Button) findViewById(R.id.button2);
        Day1.setOnClickListener(this);

        profileButton=findViewById(R.id.profile_btn);
        mealButton=findViewById(R.id.meal_btn);
        exerciseButton=findViewById(R.id.exercise_btn);
        profileButton.setOnClickListener(this);
        mealButton.setOnClickListener(this);
        exerciseButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                startActivity(new Intent(this, Day1.class));
                break;

            case R.id.profile_btn:
                Intent profileIntent = new Intent(Meal.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
            case R.id.meal_btn:
                Intent mealIntent = new Intent(Meal.this, Meal.class);
                startActivity(mealIntent);
                break;
            case R.id.exercise_btn:
                Intent exerciseIntent = new Intent(Meal.this, Exercise.class);
                startActivity(exerciseIntent);
                break;
        }
    }
}
