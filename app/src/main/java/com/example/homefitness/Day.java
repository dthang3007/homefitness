package com.example.homefitness;

public class Day {
    private String day;
    private Boolean isFinish;

    public Day(String day, boolean isFinish) {
        this.day = day;
        this.isFinish = isFinish;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


    public Boolean getFinish() {
        return isFinish;
    }

    public void setFinish(Boolean finish) {
        isFinish = finish;
    }
}
