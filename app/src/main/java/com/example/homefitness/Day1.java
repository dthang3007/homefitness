package com.example.homefitness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Day1 extends AppCompatActivity implements View.OnClickListener {
    private ImageButton Standard;
    private ImageButton Vegetarian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_1);
        Standard = (ImageButton) findViewById(R.id.imageButton3);
        Standard.setOnClickListener(this);

        setContentView(R.layout.activity_day_1);
        Vegetarian = (ImageButton) findViewById(R.id.imageButton);
        Vegetarian.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton3:
                startActivity(new Intent(this, Standard.class));
                break;
            case R.id.imageButton:
                startActivity(new Intent(this, Vegetarian.class));
                break;
        }
    }
}