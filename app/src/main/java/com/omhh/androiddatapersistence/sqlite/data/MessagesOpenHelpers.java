package com.omhh.androiddatapersistence.sqlite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MessagesOpenHelpers extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final Short DATABASE_VERSION = 1;

    public MessagesOpenHelpers(Context context, String name) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE " +
            MessagesContract.MessagesEntry.TABLE_NAME + " (" +
            MessagesContract.MessagesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MessagesContract.MessagesEntry.COLLUMN_MESSAGE + " TEXT " +
                ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ MessagesContract.MessagesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insert(String message){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MessagesContract.MessagesEntry.COLLUMN_MESSAGE, message);

        db.insert(MessagesContract.MessagesEntry.TABLE_NAME, null, contentValues);
    }

    public List<String> query(@Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(
            MessagesContract.MessagesEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        );

        ArrayList<String> messages = new ArrayList<>();
        while(cursor.moveToNext()){
            messages.add(cursor.getString(cursor.getColumnIndex(MessagesContract.MessagesEntry.COLLUMN_MESSAGE)));
        }
        return messages;
    }
}
