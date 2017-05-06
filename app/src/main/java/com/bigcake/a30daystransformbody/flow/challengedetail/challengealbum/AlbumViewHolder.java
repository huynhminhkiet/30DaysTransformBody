package com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.manager.ChallengeImageManager;
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

    public void bind(final ChallengeDayImage challengeDayImage, ChallengeImageManager challengeImageManager) {
        challengeImageManager.displayThumbnail(challengeDayImage.getChallengeDay().getId(), new ChallengeImageManager.DisplayImageCallback() {
            @Override
            public void onImageLoaded(byte[] thumbnail) {
                Glide.with(itemView.getContext()).load(thumbnail).into(ivImage);
                if (challengeDayImage.getStatus() == ChallengeDayImage.SELECTED) {
                    frameSelected.setVisibility(View.VISIBLE);
                } else {
                    frameSelected.setVisibility(View.GONE);
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

    }
}
