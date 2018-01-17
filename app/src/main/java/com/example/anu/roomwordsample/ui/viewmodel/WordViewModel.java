package com.example.anu.roomwordsample.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.anu.roomwordsample.data.Word;
import com.example.anu.roomwordsample.data.WordRepository;

import java.util.List;

/**
 * Created by Design on 17-01-2018.
 */

public class WordViewModel extends AndroidViewModel{

    private WordRepository wordRepository;

    //variable to cache the list of words.
    private LiveData<List<Word>> mWords;

    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
        mWords = wordRepository.getAllWords();
    }

    /**
     * getter for all the words. This completely hides the implementation from the UI.
     * @return all the words
     */
    public LiveData<List<Word>> getAllWords() {
        return mWords;
    }

    /**
     * wrapper method insert() that calls the repository's insert() method.
     * In this way, the implementation of insert() is completely hidden from the UI.
     * @param word the word to be inserted
     */
    public void insert(Word word){
        wordRepository.insertWord(word);
    }
}
