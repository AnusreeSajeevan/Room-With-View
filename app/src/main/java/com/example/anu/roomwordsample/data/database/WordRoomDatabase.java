package com.example.anu.roomwordsample.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.anu.roomwordsample.data.Word;

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    private static WordRoomDatabase newInstance;

    //abstract getter method for the dao
    public abstract WordDao getWordDao();

    /**
     * Make the WordRoomDatabase a singleton to prevent having multiple instances of the database opened at the same time.
     * @return
     */
    public static WordRoomDatabase getNewInstance(Context context){
        if (null == newInstance){
            synchronized (WordRoomDatabase.class){
                if (null == newInstance){
                    //create database
                    newInstance = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database").build();
                }
            }
        }
        return newInstance;
    }
}
