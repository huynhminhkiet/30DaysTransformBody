package com.bigcake.a30daystransformbody;

import com.bigcake.a30daystransformbody.data.FakeChallengeDataSource;
import com.bigcake.a30daystransformbody.data.FakeExerciseDataSource;
import com.bigcake.a30daystransformbody.data.source.repository.ChallengeRepository;
import com.bigcake.a30daystransformbody.data.source.repository.ExerciseRepository;

/**
 * Created by kiethuynh on 05/04/2017
 */

public class Injection {
    public static ExerciseRepository provideExerciseCategoriesRepository() {
        return ExerciseRepository.getInstance(FakeExerciseDataSource.getInstance());
    }

    public static ChallengeRepository provideChallengeRepository() {
        return ChallengeRepository.getInstance(FakeChallengeDataSource.getInstance());
    }
}
