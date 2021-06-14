package com.example.room_livedata_viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WordViewModel extends AndroidViewModel
{
    private final WordRepository wordRepository;
    private final LiveData<List<Word_Entity>> allWords;

    public WordViewModel(@NonNull @NotNull Application application)
    {
        super(application);

        wordRepository = new WordRepository(application);
        allWords = wordRepository.getAllWordsFromLiveData();
    }

    LiveData<List<Word_Entity>> getAllWords()
    {
        return allWords;
    }

    void insert (Word_Entity word)
    {
        wordRepository.insertWord(word);
    }

    void delete(Word_Entity delWordEntity)
    {
        wordRepository.delete(delWordEntity);
    }
}
