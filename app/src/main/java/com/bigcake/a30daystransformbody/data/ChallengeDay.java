package com.bigcake.a30daystransformbody.data;

/**
 * Created by Big Cake on 4/12/2017
 */

public class ChallengeDay {
    private int id;
    private String day;
    private int image;
    private int status;
    private ChallengeLevel level;

    public ChallengeDay() {
    }

    public ChallengeDay(int id, String day, int image, int status, ChallengeLevel level) {
        this.id = id;
        this.day = day;
        this.image = image;
        this.status = status;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ChallengeLevel getLevel() {
        return level;
    }

    public void setLevel(ChallengeLevel level) {
        this.level = level;
    }
}
