package com.mad.moviemobileapplication;

import android.provider.BaseColumns;

public  final class DatabaseMaster {
    private DatabaseMaster(){}

    public static class Users implements BaseColumns{
        public static final String TABLE_NAME="users";
        public static final String COLUMN_NAME_USERNAME="username";
        public static final String COLUMN_NAME_PASSWORD="password";
        public static final String COLUMN_NAME_USERTYPE="userType";
    }
    public static class Game implements BaseColumns{
        public static final String TABLE_NAME="games";
        public static final String COLUMN_NAME_GAMENAME="gameName";
        public static final String COLUMN_NAME_YEAR="gameYear";
    }
    public static class Comments implements BaseColumns{
        public static final String TABLE_NAME="comments";
        public static final String COLUMN_NAME_GAMENAME="gameName";
        public static final String COLUMN_NAME_RATING="rating";
        public static final String COLUMN_NAME_COMMENTS="comments";
    }







}
