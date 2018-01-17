package com.example.anu.roomwordsample.data.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

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
                            WordRoomDatabase.class, "word_database").addCallback(callback).build();
                }
            }
        }
        return newInstance;
    }

    /**
     * To delete all content and repopulate the database whenever the app is started, you create a RoomDatabase.Callback
     * and override onOpen(). Because you cannot do Room database operations on the UI thread,
     * onOpen() creates and executes an AsyncTask to add content to the database.
     */
    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new WordsInsertAsyncTask(newInstance).execute();
        }
    };

    /**
     * class which deletes the contents of the database, then populates it with new words
     */
    public static class WordsInsertAsyncTask extends AsyncTask<Void, Void, Void>{

        private WordDao wordDao;

        public WordsInsertAsyncTask(WordRoomDatabase database) {
            this.wordDao = database.getWordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAllWords();

            Word word1 = new Word("Hai");
            wordDao.insertWord(word1);

            Word word2 = new Word("Hello");
            wordDao.insertWord(word2);

            Word word3 = new Word("How");
            wordDao.insertWord(word3);

            Word word4 = new Word("Are");
            wordDao.insertWord(word4);

            Word word5 = new Word("You?");
            wordDao.insertWord(word5);

            return null;
        }
    }
}
