package com.bigcake.a30daystransformbody.flow.exercisedetail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bigcake.a30daystransformbody.R;

import java.util.List;

/**
 * Created by Big Cake on 4/7/2017
 */

public class ExerciseDescriptionAdapter extends RecyclerView.Adapter<ExerciseDescViewHolder> {
    private List<String> mDescriptionList;

    public ExerciseDescriptionAdapter(List<String> descriptionList) {
        mDescriptionList = descriptionList;
    }

    @Override
    public ExerciseDescViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ExerciseDescViewHolder(inflater.inflate(R.layout.item_exercise_description, parent, false));
    }

    @Override
    public void onBindViewHolder(ExerciseDescViewHolder holder, int position) {
        holder.tvDesciption.setText(mDescriptionList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDescriptionList.size();
    }

    public void replaceAllData(List<String> descriptionList) {
        mDescriptionList = descriptionList;
        notifyDataSetChanged();
    }
}
