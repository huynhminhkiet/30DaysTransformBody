package com.bigcake.a30daystransformbody.flow.challengedetail.changealbum;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.data.ChallengeImage;
import com.bigcake.a30daystransformbody.manager.ChallengeImageManager;
import com.bumptech.glide.Glide;

/**
 * Created by kiethuynh on 24/04/2017
 */

public class ChangeViewHolder extends RecyclerView.ViewHolder {
    ImageView ivImage;
    View frameSelected;

    public ChangeViewHolder(View itemView) {
        super(itemView);
        ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
        frameSelected = itemView.findViewById(R.id.frame_selected);
    }

    public void bind(final ChallengeImage challengeImage, ChallengeImageManager challengeImageManager) {
        frameSelected.setVisibility(View.GONE);
        challengeImageManager.displayChangeThumbnail(challengeImage.getId(), new ChallengeImageManager.DisplayImageCallback() {
            @Override
            public void onImageLoaded(byte[] thumbnail) {
                Glide.with(itemView.getContext()).load(thumbnail).into(ivImage);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

    }
}
