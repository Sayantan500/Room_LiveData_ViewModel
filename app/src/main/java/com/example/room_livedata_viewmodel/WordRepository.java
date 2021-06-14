package com.example.room_livedata_viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository
{
    private final Dao_word_entity wordDao;
    private final LiveData<List<Word_Entity>> allWordsLiveData;

    WordRepository(Application application)
    {
        wordRoomDatabase roomDatabase = wordRoomDatabase.getDatabaseInstance(application);
        wordDao = roomDatabase.wordDao();
        allWordsLiveData = wordDao.getAlphabetizedWords();
    }

    LiveData<List<Word_Entity>> getAllWordsFromLiveData()
    {
        return allWordsLiveData;
    }//Wrapper class over allWordsLiveData

    void insertWord(Word_Entity word)
    {
        wordRoomDatabase.databaseWriteExec.execute(() -> wordDao.insertIntoDB(word));//Inserts data into DB in a background thread separately from UI thread
    }//Wrapper class over insertIntoDB method

    public void delete(Word_Entity delWordEntity)
    {
        wordRoomDatabase.databaseWriteExec.execute(() -> {
            wordDao.deleteFromDB(delWordEntity); //deletes specific data from database in background thread
        });
    }
}
