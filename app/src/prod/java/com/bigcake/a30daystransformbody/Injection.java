package com.bigcake.a30daystransformbody;

import com.bigcake.a30daystransformbody.data.repository.ExerciseRepository;
import com.bigcake.a30daystransformbody.data.source.local.ExerciseLocalDataSource;

/**
 * Created by kiethuynh on 05/04/2017
 */

public class Injection {
    public static ExerciseRepository provideExerciseCategoriesRepository() {
        return ExerciseRepository.getInstance(ExerciseLocalDataSource.getInstance());
    }
}
