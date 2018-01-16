package com.example.anu.roomwordsample.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.anu.roomwordsample.data.database.WordDao;
import com.example.anu.roomwordsample.data.database.WordRoomDatabase;

import java.util.List;

public class WordRepository {

    private WordDao wordDao;
    private LiveData<List<Word>> allWords;

    public WordRepository(Application application){
        WordRoomDatabase wordRoomDatabase = WordRoomDatabase.getNewInstance(application);
        wordDao = wordRoomDatabase.getWordDao();
        allWords = wordDao.getAllWords();
    }

    /**
     * Add a wrapper for getAllWords(). Room executes all queries on a separate thread.
     * Observed LiveData will notify the observer when the data has changed.
     */
    public LiveData<List<Word>> getAllWords() {
        return allWords;
    }

    /**
     * Add a wrapper for the insert() method. You must call this on a non-UI thread or your app will crash.
     * Room ensures that you don't do any long-running operations on the main thread, blocking the UI
     */
    public void insertWord(Word word){
        new InsertAsyncTask(wordDao).execute(word);
    }

    public static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao wordDao;

        public InsertAsyncTask(WordDao wordDao){
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWord(words[0]);
            return null;
        }
    }
}
