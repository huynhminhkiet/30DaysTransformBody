package com.bigcake.a30daystransformbody.flow.exercises;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigcake.a30daystransformbody.R;
import com.bigcake.a30daystransformbody.data.Exercise;

import java.util.List;

/**
 * Created by Big Cake on 4/9/2017
 */

class ExerciseAdapter extends RecyclerView.Adapter<ExerciseViewHolder> {
    private List<Exercise> mExerciseList;
    private ExerciseAdapterListener mListener;

    public ExerciseAdapter(List<Exercise> exerciseList, ExerciseAdapterListener listener) {
        mExerciseList = exerciseList;
        mListener = listener;
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExerciseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercise, parent, false));
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        final Exercise exercise = mExerciseList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onExerciseItemClick(exercise);
            }
        });
        holder.tvTitle.setText(exercise.getTitle());
        holder.tvTag.setText(exercise.getTag());
        if (exercise.getDay() >= 0) {
            holder.tvProgressDay.setVisibility(View.VISIBLE);
            holder.tvProgressDay.setText(String.format(holder.itemView.getContext().getString(R.string.gen_progress_day),
                    String.valueOf(exercise.getDay())));
        } else {
            holder.tvProgressDay.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }

    public void replaceAllData(List<Exercise> exerciseList) {
        mExerciseList = exerciseList;
        notifyDataSetChanged();
    }

    public interface ExerciseAdapterListener {
        void onExerciseItemClick(Exercise exercise);
    }
}
