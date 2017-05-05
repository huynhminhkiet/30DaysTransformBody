package com.bigcake.a30daystransformbody.data;

import java.io.Serializable;

/**
 * Created by Big Cake on 4/12/2017
 */

public class ChallengeDay implements Serializable {
    public static final int STATUS_DONE = 1;
    public static final int STATUS_CURRENT = 2;
    public static final int STATUS_IN_PROGRESS= 0;

    private int id;
    private int challengeId;
    private int day;
    private int status;
    private ChallengeLevel level;
    private String image;

    public ChallengeDay() {
    }

    public ChallengeDay(int id, int challengeId, int day, int status, ChallengeLevel level, String image) {
        this.id = id;
        this.challengeId = challengeId;
        this.day = day;
        this.status = status;
        this.level = level;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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

    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
