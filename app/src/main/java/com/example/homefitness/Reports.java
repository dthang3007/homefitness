package com.example.homefitness;

import static com.example.homefitness.BMICalcUtil.BMI_CATEGORY_HEALTHY;
import static com.example.homefitness.BMICalcUtil.BMI_CATEGORY_OBESE;
import static com.example.homefitness.BMICalcUtil.BMI_CATEGORY_OVERWEIGHT;
import static com.example.homefitness.BMICalcUtil.BMI_CATEGORY_UNDERWEIGHT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Reports extends AppCompatActivity implements View.OnClickListener{
    private EditText weightKgEditText, heightCmEditText;
    private EditText weightLbsEditText, heightFtEditText, heightInEditText;
    private Button calculateButton;
    private TextView bmiTextView, categoryTextView;
    private ToggleButton toggleUnitsButton;
    private CardView bmiResultCardView;

    private Button profileButton;
    private Button mealButton;
    private Button exerciseButton;

    private boolean inMetricUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        weightKgEditText = findViewById(R.id.activity_main_weightkgs);
        heightCmEditText = findViewById(R.id.activity_main_heightcm);

        weightLbsEditText = findViewById(R.id.activity_main_weightlbs);
        heightFtEditText = findViewById(R.id.activity_main_heightfeet);
        heightInEditText = findViewById(R.id.activity_main_heightinches);

        calculateButton = findViewById(R.id.activity_main_calculate);
        toggleUnitsButton = findViewById(R.id.activity_main_toggleunits);

        bmiTextView = findViewById(R.id.activity_main_bmi);
        categoryTextView = findViewById(R.id.activity_main_category);
        bmiResultCardView = findViewById(R.id.activity_main_resultcard);

        profileButton=findViewById(R.id.profile_btn);
        mealButton=findViewById(R.id.meal_btn);
        exerciseButton=findViewById(R.id.exercise_btn);
        profileButton.setOnClickListener(this);
        mealButton.setOnClickListener(this);
        exerciseButton.setOnClickListener(this);

        inMetricUnits = true;
        updateInputsVisibility();
        bmiResultCardView.setVisibility(View.GONE);

        calculateButton.setOnClickListener(view -> {
            if (inMetricUnits) {
                if (weightKgEditText.length() == 0 || heightCmEditText.length() == 0) {
                    Toast.makeText(Reports.this, "Populate Weight and Height to Calculate BMI", Toast.LENGTH_SHORT).show();
                } else {
                    double heightInCms = Double.parseDouble(heightCmEditText.getText().toString());
                    double weightInKgs = Double.parseDouble(weightKgEditText.getText().toString());
                    double bmi = BMICalcUtil.getInstance().calculateBMIMetric(heightInCms, weightInKgs);
                    displayBMI(bmi);
                }
            } else {
                if (weightLbsEditText.length() == 0 || heightFtEditText.length() == 0 || heightInEditText.length() == 0) {
                    Toast.makeText(Reports.this, "Populate Weight and Height to Calculate BMI", Toast.LENGTH_SHORT).show();
                } else {
                    double heightFeet = Double.parseDouble(heightFtEditText.getText().toString());
                    double heightInches = Double.parseDouble(heightInEditText.getText().toString());
                    double weightLbs = Double.parseDouble(weightLbsEditText.getText().toString());
                    double bmi = BMICalcUtil.getInstance().calculateBMIImperial(heightFeet, heightInches, weightLbs);
                    displayBMI(bmi);
                }
            }
        });

        toggleUnitsButton.setOnClickListener(view -> {
            inMetricUnits = !inMetricUnits;
            updateInputsVisibility();
        });
    }

    private void updateInputsVisibility() {
        if (inMetricUnits) {
            heightCmEditText.setVisibility(View.VISIBLE);
            weightKgEditText.setVisibility(View.VISIBLE);
            heightFtEditText.setVisibility(View.GONE);
            heightInEditText.setVisibility(View.GONE);
            weightLbsEditText.setVisibility(View.GONE);
        } else {
            heightCmEditText.setVisibility(View.GONE);
            weightKgEditText.setVisibility(View.GONE);
            heightFtEditText.setVisibility(View.VISIBLE);
            heightInEditText.setVisibility(View.VISIBLE);
            weightLbsEditText.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("DefaultLocale")
    private void displayBMI(double bmi) {
        bmiResultCardView.setVisibility(View.VISIBLE);

        bmiTextView.setText(String.format("%.2f", bmi));

        String bmiCategory = BMICalcUtil.getInstance().classifyBMI(bmi);
        categoryTextView.setText(bmiCategory);

        switch (bmiCategory) {
            case BMI_CATEGORY_UNDERWEIGHT:
                bmiResultCardView.setCardBackgroundColor(Color.YELLOW);
                break;
            case BMI_CATEGORY_HEALTHY:
                bmiResultCardView.setCardBackgroundColor(Color.GREEN);
                break;
            case BMI_CATEGORY_OVERWEIGHT:
                bmiResultCardView.setCardBackgroundColor(Color.BLUE);
                break;
            case BMI_CATEGORY_OBESE:
                bmiResultCardView.setCardBackgroundColor(Color.RED);
                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_btn:
                Intent profileIntent = new Intent(Reports.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
            case R.id.meal_btn:
                Intent mealIntent = new Intent(Reports.this, Meal.class);
                startActivity(mealIntent);
                break;
            case R.id.exercise_btn:
                Intent exerciseIntent = new Intent(Reports.this, Exercise.class);
                startActivity(exerciseIntent);
                break;
        }
    }
}