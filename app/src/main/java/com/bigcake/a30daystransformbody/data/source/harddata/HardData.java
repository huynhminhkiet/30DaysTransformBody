package com.bigcake.a30daystransformbody.data.source.harddata;

import com.bigcake.a30daystransformbody.data.Exercise;

import java.util.List;

/**
 * Created by Big Cake on 5/12/2017
 */

public interface HardData {
    void saveExercises(SaveExercisesCallback callback);

    interface SaveExercisesCallback {
        void onSuccess();
        void onError();
    }
}
