package com.example.room_livedata_viewmodel;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import io.reactivex.rxjava3.annotations.NonNull;

@androidx.room.Entity(tableName = "WORDS Table")
class Word_Entity
{
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private final String word;

    public Word_Entity(@NonNull String word)
    {
        this.word = word;
    }

    @org.jetbrains.annotations.NotNull
    public String getWord() {
        return this.word;
    }
}
