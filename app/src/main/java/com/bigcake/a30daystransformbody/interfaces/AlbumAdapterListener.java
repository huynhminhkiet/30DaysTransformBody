package com.bigcake.a30daystransformbody.interfaces;

import com.bigcake.a30daystransformbody.data.ChallengeDay;

/**
 * Created by kiethuynh on 26/04/2017
 */

public interface AlbumAdapterListener extends ItemClickListener<ChallengeDay> {
    void onItemLongClick(ChallengeDay challengeDay, int position);
}
