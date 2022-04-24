package com.example.homefitness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Meal extends AppCompatActivity implements View.OnClickListener {
    private Button Day1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        Day1 = (Button) findViewById(R.id.button2);
        Day1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                startActivity(new Intent(this, Day1.class));
                break;
        }
    }
}
