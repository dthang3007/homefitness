package com.example.homefitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Exercise extends AppCompatActivity implements View.OnClickListener {
    private Button armButton;
    private Button legButton;
    private Button absButton;
    private Button buttButton;
    private Button chestButton;

    private Button profileButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        armButton=findViewById(R.id.arm_btn);
        legButton=findViewById(R.id.leg_btn);
        absButton=findViewById(R.id.abs_btn);
        buttButton=findViewById(R.id.butt_btn);
        chestButton=findViewById(R.id.chest_btn);
        profileButton=findViewById(R.id.profile_btn);

        armButton.setOnClickListener(this);
        legButton.setOnClickListener(this);
        absButton.setOnClickListener(this);
        buttButton.setOnClickListener(this);
        chestButton.setOnClickListener(this);
        profileButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.arm_btn:
//                List<Pack> packList=new ArrayList<>();
//                Pack jumpingPack=new Pack("Jumping Pack",18);
//                Pack plankPack =new Pack("Plank",10);
//                packList.add(jumpingPack);
//                packList.add(plankPack);
                String name ="arm_pack";
                Intent i=new Intent(Exercise.this,Exercise_Set.class);
                i.putExtra("pack",name);
                startActivity(i);
                break;
            case R.id.abs_btn:
                break;
            case R.id.leg_btn:
                break;
            case R.id.chest_btn:
                break;
            case R.id.butt_btn:
                break;
            case R.id.profile_btn:
                Intent profileIntent = new Intent(Exercise.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
        }
    }
}