package com.bigcake.a30daystransformbody;

import com.bigcake.a30daystransformbody.data.repository.ExerciseCategoriesRepository;
import com.bigcake.a30daystransformbody.data.source.local.ExerciseCategoriesLocalDataSource;

/**
 * Created by kiethuynh on 05/04/2017
 */

public class Injection {
    public static ExerciseCategoriesRepository provideExerciseCategoriesRepository() {
        return ExerciseCategoriesRepository.getInstance(ExerciseCategoriesLocalDataSource.getInstance());
    }
}
