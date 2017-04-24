package com.bigcake.a30daystransformbody.flow.challengedetail.challengeprogress;

import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by Big Cake on 4/12/2017
 */

class ChallengeViewHolder extends RecyclerView.ViewHolder {
    TextView tvDay;
    ImageView ivDay;
    public ChallengeViewHolder(View itemView) {
        super(itemView);
        tvDay = (TextView) itemView.findViewById(R.id.tv_day);
        ivDay = (ImageView) itemView.findViewById(R.id.iv_day);
    }

    public void bind(ChallengeDay challengeDay) {
        if (challengeDay.getStatus() == ChallengeDay.STATUS_DONE) {
            tvDay.setText("");
            tvDay.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorDarkGreen));
            tvDay.setBackgroundResource(R.drawable.shape_challenge_day_done);
            ivDay.setVisibility(View.VISIBLE);
            if (challengeDay.getImage() != null)
                Glide.with(itemView.getContext()).load(challengeDay.getImage()).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivDay) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(itemView.getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        ivDay.setImageDrawable(circularBitmapDrawable);
                    }
                });
            else {
                Glide.with(itemView.getContext()).load(R.drawable.ic_camera).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivDay) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(itemView.getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        ivDay.setImageDrawable(circularBitmapDrawable);
                    }
                });
            }
        } else if (challengeDay.getStatus() == ChallengeDay.STATUS_CURRENT) {
            tvDay.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorOrange));
            tvDay.setBackgroundResource(R.drawable.shape_challenge_day_current);
            ivDay.setVisibility(View.GONE);
            tvDay.setText(challengeDay.getDay() + "");
        } else {
            tvDay.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorGreenYellow));
            tvDay.setBackgroundResource(R.drawable.shape_challenge_day);
            tvDay.setText(challengeDay.getDay() + "");
            ivDay.setVisibility(View.GONE);
        }
    }
}
