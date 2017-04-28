package com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bumptech.glide.Glide;

/**
 * Created by kiethuynh on 24/04/2017
 */

public class AlbumViewHolder extends RecyclerView.ViewHolder {
    ImageView ivImage;
    View frameSelected;

    public AlbumViewHolder(View itemView) {
        super(itemView);
        ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
        frameSelected = itemView.findViewById(R.id.frame_selected);
    }

    public void bind(ChallengeDayImage challengeDayImage) {
        Glide.with(itemView.getContext()).load(challengeDayImage.getChallengeDay().getImage()).into(ivImage);
        if (challengeDayImage.getStatus() == ChallengeDayImage.SELECTED) {
            frameSelected.setVisibility(View.VISIBLE);
        } else {
            frameSelected.setVisibility(View.GONE);
        }
    }
}
