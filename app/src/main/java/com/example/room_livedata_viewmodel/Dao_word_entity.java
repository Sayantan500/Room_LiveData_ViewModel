package com.example.room_livedata_viewmodel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Dao_word_entity
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertIntoDB(Word_Entity Word);

    @Update
    void updateOldEntity(Word_Entity edited_Word);

    @Delete
    void deleteFromDB(Word_Entity wordEntity);

    @Query("DELETE FROM WORDS_Table")
    void deleteAll();

    @Query("SELECT * FROM WORDS_Table ORDER BY word ASC")
    LiveData<List<Word_Entity>> getAlphabetizedWords();
}
