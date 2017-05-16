package com.bigcake.a30daystransformbody.interfaces;

import com.bigcake.a30daystransformbody.data.ChallengeDay;

/**
 * Created by kiethuynh on 16/05/2017
 */

public interface ChallengeDayAdapterListener {
    void requestUpdateOnDatabase(ChallengeDay challengeDay);
    void requestUpdateProgress(int day);
}
