package com.omhh.androiddatapersistence.sqlite.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MessagesOpenHelpers extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final Short DATABASE_VERSION = 1;

    public MessagesOpenHelpers(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE " +
            MessagesContract.MessagesEntry.TABLE_NAME + " (" +
            MessagesContract.MessagesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            MessagesContract.MessagesEntry.COLLUMN_MESSAGE + "TEXT " +
                ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ MessagesContract.MessagesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
