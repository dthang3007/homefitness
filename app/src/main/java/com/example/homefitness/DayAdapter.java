package com.example.homefitness;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder> {
    private List<Day> mListDay;

    public DayAdapter(List<Day> mListDay) {
        this.mListDay = mListDay;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false);

        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        Day day = (Day) mListDay.get(position);
        if (day == null) {
            return;
        }
        holder.dayButton.setText(day.getDay());
        holder.dayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Pack jumpingPack=new Pack("Jumping Pack",18);


                Intent i = new Intent(context, StartExerciseActivity.class);
                Bundle bundle =new Bundle();
                bundle.putSerializable("pack", jumpingPack);
                i.putExtras(bundle);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mListDay != null) {
            return mListDay.size();
        }
        return 0;
    }

    public class DayViewHolder extends RecyclerView.ViewHolder {
        private Button dayButton;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            dayButton = itemView.findViewById(R.id.buttonDay);

        }

    }
}
