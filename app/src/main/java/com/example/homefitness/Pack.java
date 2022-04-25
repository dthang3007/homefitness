package com.example.homefitness;

import java.io.Serializable;

public class Pack implements Serializable {
    private String namePack;
    private int timePack;

    public Pack(String namePack, int timePack) {
        this.namePack = namePack;
        this.timePack = timePack;
    }


    public String getNamePack() {
        return namePack;
    }

    public void setNamePack(String namePack) {
        this.namePack = namePack;
    }

    public int getTimePack() {
        return timePack;
    }

    public void setTimePack(int timePack) {
        this.timePack = timePack;
    }
}
