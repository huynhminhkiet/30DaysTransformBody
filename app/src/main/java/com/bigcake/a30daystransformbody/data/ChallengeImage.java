package com.bigcake.a30daystransformbody.data;

/**
 * Created by Big Cake on 5/7/2017
 */

public class ChallengeImage {
    private int id;
    private int challengeId;
    private String challengeImage;

    public ChallengeImage(int id, int challengeId, String challengeImage) {
        this.id = id;
        this.challengeId = challengeId;
        this.challengeImage = challengeImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    public String getChallengeImage() {
        return challengeImage;
    }

    public void setChallengeImage(String challengeImage) {
        this.challengeImage = challengeImage;
    }
}
