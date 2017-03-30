package com.bigcake.a30daystransfrombody.data;

/**
 * Created by kiethuynh on 30/03/2017
 */

public class ExerciseCategory {
    private String name;
    private String description;

    public ExerciseCategory(String name, String description) {
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
}
