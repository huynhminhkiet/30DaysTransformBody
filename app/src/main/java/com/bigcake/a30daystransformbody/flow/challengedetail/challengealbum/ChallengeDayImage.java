package com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum;

import com.bigcake.a30daystransformbody.data.ChallengeDay;

/**
 * Created by kiethuynh on 28/04/2017
 */

public class ChallengeDayImage {
    public static final int SELECTED = 1;
    public static final int NOT_SELECTED = 0;

    private ChallengeDay challengeDay;
    private int status;

    public ChallengeDayImage(ChallengeDay challengeDay, int status) {
        this.challengeDay = challengeDay;
        this.status = status;
    }

    public ChallengeDay getChallengeDay() {
        return challengeDay;
    }

    public void setChallengeDay(ChallengeDay challengeDay) {
        this.challengeDay = challengeDay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
