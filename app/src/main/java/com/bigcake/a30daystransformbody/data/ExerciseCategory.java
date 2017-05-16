package com.bigcake.a30daystransformbody.data;

import java.io.Serializable;

/**
 * Created by kiethuynh on 30/03/2017
 */

public class ExerciseCategory implements Serializable{
    private int id;

    private String name;
    private String description;

    public ExerciseCategory(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
