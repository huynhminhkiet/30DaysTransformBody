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
import com.bigcake.a30daystransformbody.manager.ChallengeImageManager;
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
    private ChallengeImageManager mChallengeImageManager;

    public ChallengeDayAdapter(Context context, List<ChallengeDay> challengeDayList, ChallengeImageManager challengeImageManager) {
        mContext = context;
        mChallengeDayList = challengeDayList;
        mChallengeImageManager = challengeImageManager;
    }

    @Override
    public ChallengeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChallengeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_challenge, parent, false));
    }

    @Override
    public void onBindViewHolder(final ChallengeViewHolder holder, int position) {
        final ChallengeDay challengeDay = mChallengeDayList.get(position);
        holder.bind(challengeDay, mChallengeImageManager);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(challengeDay, holder.getAdapterPosition());
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

    public void updateItem(ChallengeDay challengeDay, int position) {
        mChallengeDayList.set(position, challengeDay);
        notifyItemChanged(position);
    }

    public void updateItem(ChallengeDay challengeDay) {
        for (int i = 0; i < mChallengeDayList.size(); i++)
            if (mChallengeDayList.get(i).getId() == challengeDay.getId()) {
                mChallengeDayList.set(i, challengeDay);
                notifyItemChanged(i);
                return;
            }
    }

    public void setItemClickListener(ItemClickListener<ChallengeDay> listener) {
        mListener = listener;
    }
}
