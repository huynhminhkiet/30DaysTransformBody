package com.bigcake.a30daystransformbody.flow.challengedetail.challengeprogress;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.interfaces.ItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

/**
 * Created by Big Cake on 4/12/2017
 */

public class ChallengeDayAdapter extends RecyclerView.Adapter<ChallengeViewHolder> {
    private Context mContext;
    private List<ChallengeDay> mChallengeDayList;
    private ItemClickListener<ChallengeDay> mListener;

    public ChallengeDayAdapter(Context context, List<ChallengeDay> challengeDayList) {
        mContext = context;
        mChallengeDayList = challengeDayList;
    }

    @Override
    public ChallengeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChallengeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_challenge, parent, false));
    }

    @Override
    public void onBindViewHolder(final ChallengeViewHolder holder, int position) {
        final ChallengeDay challengeDay = mChallengeDayList.get(position);
        if (challengeDay.getStatus() == ChallengeDay.STATUS_DONE) {
            holder.tvDay.setText("");
            holder.tvDay.setTextColor(ContextCompat.getColor(mContext, R.color.colorDarkGreen));
            holder.tvDay.setBackgroundResource(R.drawable.shape_challenge_day_done);
            holder.ivDay.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(challengeDay.getImage()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.ivDay) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    holder.ivDay.setImageDrawable(circularBitmapDrawable);
                }
            });
        } else if (challengeDay.getStatus() == ChallengeDay.STATUS_CURRENT) {
            holder.tvDay.setTextColor(ContextCompat.getColor(mContext, R.color.colorOrange));
            holder.tvDay.setBackgroundResource(R.drawable.shape_challenge_day_current);
            holder.ivDay.setVisibility(View.GONE);
            holder.tvDay.setText(challengeDay.getDay());
        } else {
            holder.tvDay.setTextColor(ContextCompat.getColor(mContext, R.color.colorGreenYellow));
            holder.tvDay.setBackgroundResource(R.drawable.shape_challenge_day);
            holder.tvDay.setText(challengeDay.getDay());
            holder.ivDay.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(challengeDay);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mChallengeDayList.size();
    }

    public void replaceAllData(List<ChallengeDay> challengeDayList) {
        mChallengeDayList = challengeDayList;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener<ChallengeDay> listener) {
        mListener = listener;
    }
}
