package com.example.room_livedata_viewmodel;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Word_Entity.class} , version = 1 , exportSchema = false)
public abstract class wordRoomDatabase extends RoomDatabase
{
    public abstract Dao_word_entity wordDao();

    private static volatile wordRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExec
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /*
    *Singleton Class...
    *creates only one room database for the entire app in the application context.
    */

    static wordRoomDatabase getDatabaseInstance(final Context context)
    {
        if(INSTANCE == null)
        {
            synchronized (wordRoomDatabase.class)
            {
                if(INSTANCE == null)
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            wordRoomDatabase.class,
                            "word_database").build();
            }
        }
        return INSTANCE;
    }
}
