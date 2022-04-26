package com.example.homefitness;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise_Set extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rcvDay;
    private FirebaseAuth mAuth;
    private DatabaseReference reference;
    private FirebaseUser user;
    private String userId;
    private Button buttonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_set);
        rcvDay = findViewById(R.id.rcv_day);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 6);
        rcvDay.setLayoutManager(gridLayoutManager);
        mAuth = FirebaseAuth.getInstance();
        buttonBack=findViewById(R.id.button3);
        buttonBack.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("arm_pack");
            //The key argument here must match that used in the other activity
        }
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Days");
        userId = user.getUid();
        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, List<Map<String,Object>>> td = (Map<String, List<Map<String,Object>>>) snapshot.getValue();
                List<Map<String,Object>> convert = td.get("arm_pack");
                List<Day> days=new ArrayList<>();
                convert.forEach(e->{
                    Day day=new Day(e.get("day").toString(),Boolean.getBoolean(e.get("finish").toString()));
                    days.add(day);
                });

//                for (Map.Entry<String, Object> entry : td.entrySet()) {
//                    if("arm_pack".equals(entry.getValue())){
//                       String result = entry.getValue();
//                    }
//                }
                //                List<Day> days = (List<Day>) td.get("arm_pack");
                Log.d("test", td.get("arm_pack").toString());

                if (days.size() > 0) {
                    DayAdapter adapter = new DayAdapter(days);
                    rcvDay.setAdapter((adapter));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                String test = "test";
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button3:
                Intent i = new Intent(Exercise_Set.this,Exercise.class);
                startActivity(i);
                break;
        }
    }
}