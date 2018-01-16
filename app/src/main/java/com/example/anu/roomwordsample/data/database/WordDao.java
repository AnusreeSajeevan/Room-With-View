package com.example.anu.roomwordsample.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.anu.roomwordsample.data.Word;

import java.util.List;

@Dao
public interface WordDao {
    @Insert
    void insertWord(Word word);

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

    /**
     * method to delete all the words in the database
     */
    @Query("DELETE FROM word_table")
    void deleteAllWords();
}
