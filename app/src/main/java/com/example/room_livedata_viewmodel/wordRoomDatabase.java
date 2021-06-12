package com.example.room_livedata_viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.jetbrains.annotations.NotNull;

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
                            "word_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(new RoomDatabase.Callback()
                            {
                                /**
                                 * Called when the database is created for the first time. This is called after all the
                                 * tables are created.
                                 *
                                 * @param db The database.
                                 */
                                @Override
                                public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    databaseWriteExec.execute(() -> {
                                        Dao_word_entity daoWordEntity = INSTANCE.wordDao();
                                        daoWordEntity.deleteAll();

                                        daoWordEntity.insertIntoDB(new Word_Entity("HELLO"));
                                        daoWordEntity.insertIntoDB(new Word_Entity("WORLD"));
                                    });
                                }
                            })
                            .build();
            }
        }
        return INSTANCE;
    }
}
