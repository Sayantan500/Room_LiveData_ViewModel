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

    void insertWord(String word)
    {
        wordRoomDatabase.databaseWriteExec.execute(() -> wordDao.insertIntoDB(word));//Inserts data into DB in a background thread separately from UI thread
    }//Wrapper class over insertIntoDB method
}
