package com.bigcake.a30daystransformbody.interfaces;

import com.bigcake.a30daystransformbody.data.ChallengeDay;

/**
 * Created by Big Cake on 5/10/2017
 */

public interface ChallengeAlbumFragmentListener {
    void onChallengeDayImageDeleted(ChallengeDay challengeDay);
    void onChangeImageCreated();
}
