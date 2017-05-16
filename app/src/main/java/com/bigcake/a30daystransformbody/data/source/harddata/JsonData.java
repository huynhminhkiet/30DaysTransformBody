package com.bigcake.a30daystransformbody.data.source.harddata;

import android.os.AsyncTask;

import com.bigcake.a30daystransformbody.data.Exercise;
import com.bigcake.a30daystransformbody.data.source.ExerciseDataSource;
import com.bigcake.a30daystransformbody.data.source.repository.ExerciseRepository;
import com.bigcake.a30daystransformbody.manager.AssetsManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Big Cake on 5/12/2017
 */

public class JsonData implements HardData {
    private AssetsManager mAssetsManager;
    private ExerciseRepository mExerciseRepository;

    public JsonData(AssetsManager assetsManager, ExerciseRepository exerciseRepository) {
        mAssetsManager = assetsManager;
        mExerciseRepository = exerciseRepository;
    }

    @Override
    public void saveExercises(final SaveExercisesCallback callback) {
        new AsyncTask<Void, Void, Void>() {
            List<Exercise> exerciseList = null;
            @Override
            protected Void doInBackground(Void... params) {
                String json = mAssetsManager.convertJsonFileToString("json/exercises.json");
                try {
                    exerciseList = new ArrayList<>();
                    JSONArray testsArray = new JSONArray(json);
                    for (int i = 0; i < testsArray.length(); i++) {
                        JSONObject jsonObject = testsArray.getJSONObject(i);
                        final Exercise exercise = new Exercise(jsonObject.getInt("id"), jsonObject.getInt("categoryId"),
                                jsonObject.getString("title"), jsonObject.getString("tag"), jsonObject.getString("images"), jsonObject.getString("descriptions"), -1);
                        mExerciseRepository.saveExercise(exercise, new ExerciseDataSource.DefaultCallback() {
                            @Override
                            public void onSuccess() {
                                exerciseList.add(exercise);
                            }

                            @Override
                            public void onError() {

                            }
                        });
                    }
                } catch (JSONException e) {
                    exerciseList = null;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (exerciseList != null)
                    callback.onSuccess();
                else
                    callback.onError();
            }
        }.execute();
    }
}
