package com.bigcake.a30daystransformbody.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Big Cake on 4/23/2017
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ThirtyDaysTransformBody.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String BLOB_TYPE = " BLOB";
    private static final String COMMA_SEP = ",";

    private static DatabaseHelper mInstance;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (mInstance == null)
            mInstance = new DatabaseHelper(context);
        return mInstance;
    }


    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CHALLENGE_DAY_TABLE);
        db.execSQL(SQL_CREATE_CHALLENGE_IMAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }

    private static final String SQL_CREATE_CHALLENGE_DAY_TABLE =
            "CREATE TABLE " + TableContent.ChallengeDay.TABLE_NAME + " (" +
                    TableContent.ChallengeDay._ID + INTEGER_TYPE +" PRIMARY KEY AUTOINCREMENT," +
                    TableContent.ChallengeDay.COLUMN_EXERCISE_ID + INTEGER_TYPE + COMMA_SEP +
                    TableContent.ChallengeDay.COLUMN_DATE + INTEGER_TYPE + COMMA_SEP +
                    TableContent.ChallengeDay.COLUMN_IMAGE + TEXT_TYPE + COMMA_SEP +
                    TableContent.ChallengeDay.COLUMN_THUMBNAIL + BLOB_TYPE + COMMA_SEP +
                    TableContent.ChallengeDay.COLUMN_STATUS + INTEGER_TYPE + COMMA_SEP +
                    TableContent.ChallengeDay.COLUMN_LEVEL_ID + INTEGER_TYPE +
                    " )";

    private static final String SQL_CREATE_CHALLENGE_IMAGE =
            "CREATE TABLE " + TableContent.ChallengeImage.TABLE_NAME + " (" +
                    TableContent.ChallengeImage._ID + INTEGER_TYPE +" PRIMARY KEY AUTOINCREMENT," +
                    TableContent.ChallengeImage.COLUMN_EXERCISE_ID + INTEGER_TYPE + COMMA_SEP +
                    TableContent.ChallengeImage.COLUMN_CHANGE_THUMBNAIL + BLOB_TYPE + COMMA_SEP +
                    TableContent.ChallengeImage.COLUMN_CHANGE_IMAGE + TEXT_TYPE +
                    " )";
}
