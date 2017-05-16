package com.bigcake.a30daystransformbody.data.source.local;

/**
 * Created by Big Cake on 4/23/2017
 */

public class TableContent {
    public abstract class ChallengeDay {
        public static final String TABLE_NAME = "challenge_day";
        public static final String _ID = "id";
        public static final String COLUMN_EXERCISE_ID = "challengeId";
        public static final String COLUMN_DATE = "challengeDate";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_THUMBNAIL = "thumbnail";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_LEVEL_ID = "levelId";
    }

    public abstract class ChallengeImage {
        public static final String TABLE_NAME = "challenge_image";
        public static final String _ID= "id";
        public static final String COLUMN_EXERCISE_ID = "challenge_id";
        public static final String COLUMN_CHANGE_THUMBNAIL = "change_thumbnail";
        public static final String COLUMN_CHANGE_IMAGE = "change_image";
    }

    public abstract class Weight {
        public static final String TABLE_NAME = "weight";
        public static final String _ID= "id";
        public static final String COLUMN_WEIGHT = "weight";
        public static final String COLUMN_DATE = "date";
    }

    public abstract class Exercise {
        public static final String TABLE_NAME = "exercise";
        public static final String _ID = "id";
        public static final String COLUMN_CATEGORY_ID = "exercise_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_TAG = "tag";
        public static final String COLUMN_IMAGES = "images";
        public static final String COLUMN_DESCRIPTIONS = "descriptions";
        public static final String COLUMN_DAY = "progressDay";
    }
}
