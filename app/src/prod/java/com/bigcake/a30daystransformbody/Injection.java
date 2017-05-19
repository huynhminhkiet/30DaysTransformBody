package com.bigcake.a30daystransformbody;

import android.content.Context;

import com.bigcake.a30daystransformbody.data.source.PreferencesData;
import com.bigcake.a30daystransformbody.data.source.harddata.HardData;
import com.bigcake.a30daystransformbody.data.source.harddata.JsonData;
import com.bigcake.a30daystransformbody.data.source.local.ChallengeLocalDataSource;
import com.bigcake.a30daystransformbody.data.source.local.ExerciseLocalDataSource;
import com.bigcake.a30daystransformbody.data.source.preferences.AppPreferences;
import com.bigcake.a30daystransformbody.data.source.repository.ChallengeRepository;
import com.bigcake.a30daystransformbody.data.source.repository.ExerciseRepository;
import com.bigcake.a30daystransformbody.manager.AssetsManager;

/**
 * Created by kiethuynh on 05/04/2017
 */

public class Injection {
    public static ExerciseRepository provideExerciseCategoriesRepository(Context context) {
        return ExerciseRepository.getInstance(ExerciseLocalDataSource.getInstance(context));
    }

    public static ChallengeRepository provideChallengeRepository(Context context) {
        return ChallengeRepository.getInstance(ChallengeLocalDataSource.getInstance(context));
    }

    public static HardData provideHardData(Context context) {
        return new JsonData(new AssetsManager(context), ExerciseRepository.getInstance(ExerciseLocalDataSource.getInstance(context)));
    }

    public static PreferencesData providePreferencesData(Context context) {
        return AppPreferences.getInstance(context);
    }
}
