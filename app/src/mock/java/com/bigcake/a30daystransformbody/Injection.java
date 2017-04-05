package com.bigcake.a30daystransformbody;

import com.bigcake.a30daystransformbody.data.FakeExerciseCategoriesDataSource;
import com.bigcake.a30daystransformbody.data.repository.ExerciseCategoriesRepository;

/**
 * Created by kiethuynh on 05/04/2017
 */

public class Injection {
    public static ExerciseCategoriesRepository provideExerciseCategoriesRepository() {
        return ExerciseCategoriesRepository.getInstance(FakeExerciseCategoriesDataSource.getInstance());
    }
}
