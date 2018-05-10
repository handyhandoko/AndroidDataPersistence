package com.omhh.androiddatapersistence.sqlite.data;

import android.provider.BaseColumns;

public class MessagesContract {

    private MessagesContract() {}

    public static final class MessagesEntry implements BaseColumns{
        public static final String TABLE_NAME = "messages";

        public static final String COLLUMN_MESSAGE = "message";
    }
}
