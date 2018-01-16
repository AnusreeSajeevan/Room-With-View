package com.example.anu.roomwordsample.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    /**
     * word act as it's own primary key
     * it cannot be null
     * @param mWord
     */
    public Word(String mWord) {
        this.mWord = mWord;
    }

    public String getmWord() {
        return mWord;
    }
}
