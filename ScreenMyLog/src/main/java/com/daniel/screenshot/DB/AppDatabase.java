package com.daniel.screenshot.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Log.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract LogDao logDao();

    public static final String LOG_DATABASE_NAME = "LogDb";
    public static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, LOG_DATABASE_NAME).build();
        }
        return instance;
    }
}
