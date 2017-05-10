package com.bigcake.a30daystransformbody.data;

import java.util.Date;

/**
 * Created by Big Cake on 5/11/2017
 */

public class Weight {
    private int id;
    private Date date;
    private float weight;

    public Weight(int id, Date date, float weight) {
        this.id = id;
        this.date = date;
        this.weight = weight;
    }

    public Weight(Date date, float weight) {
        this.date = date;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
