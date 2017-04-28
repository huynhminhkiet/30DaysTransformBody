package com.bigcake.a30daystransformbody.interfaces;

import com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum.ChallengeDayImage;

/**
 * Created by kiethuynh on 26/04/2017
 */

public interface AlbumAdapterListener extends ItemClickListener<ChallengeDayImage> {
    void onItemLongClick(ChallengeDayImage challengeDayImage, int position);
}
