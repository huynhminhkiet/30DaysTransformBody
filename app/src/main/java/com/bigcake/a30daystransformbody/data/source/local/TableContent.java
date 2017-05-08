package com.bigcake.a30daystransformbody.data.source.local;

/**
 * Created by Big Cake on 4/23/2017
 */

public class TableContent {
    public abstract class ChallengeDay {
        public static final String TABLE_NAME = "challenge_day";
        public static final String _ID = "id";
        public static final String COLUMN_CHALLENGE_ID = "challengeId";
        public static final String COLUMN_DATE = "challengeDate";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_THUMBNAIL = "thumbnail";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_LEVEL_ID = "levelId";
    }

    public abstract class ChallengeImage {
        public static final String TABLE_NAME = "challenge_image";
        public static final String _ID= "id";
        public static final String COLUMN_CHALLENGE_ID = "challenge_id";
        public static final String COLUMN_CHANGE_THUMBNAIL = "change_thumbnail";
        public static final String COLUMN_CHANGE_IMAGE = "change_image";
    }
}
