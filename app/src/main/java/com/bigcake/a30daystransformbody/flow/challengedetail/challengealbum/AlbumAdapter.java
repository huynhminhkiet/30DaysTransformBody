package com.bigcake.a30daystransformbody.flow.challengedetail.challengealbum;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.data.ChallengeDay;
import com.bigcake.a30daystransformbody.interfaces.ItemClickListener;

import java.util.List;

/**
 * Created by kiethuynh on 24/04/2017
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {
    private Context mContext;
    private List<ChallengeDay> mChallengeDayList;
    private ItemClickListener<ChallengeDay> mListener;

    public AlbumAdapter(Context context, List<ChallengeDay> challengeDayList) {
        mContext = context;
        mChallengeDayList = challengeDayList;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlbumViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_album, parent, false));
    }

    @Override
    public void onBindViewHolder(final AlbumViewHolder holder, int position) {
        final ChallengeDay challengeDay = mChallengeDayList.get(position);
        holder.bind(challengeDay);
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

    public void setItemClickListener(ItemClickListener<ChallengeDay> listener) {
        mListener = listener;
    }
}
