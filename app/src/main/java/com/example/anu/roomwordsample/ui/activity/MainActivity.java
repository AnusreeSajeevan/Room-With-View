package com.example.anu.roomwordsample.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.anu.roomwordsample.R;
import com.example.anu.roomwordsample.data.Word;
import com.example.anu.roomwordsample.ui.adapter.WordViewAdapter;
import com.example.anu.roomwordsample.ui.viewmodel.WordViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private WordViewAdapter wordViewAdapter;

    private static final int NEW_WORD_REQUEST_CODE = 10;

    //member variable for the ViewModel
    private WordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get a ViewModel from the ViewModelProvider
        viewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        /**
         * add an observer for the LiveData returned by getAllWords().
         The onChanged() method fires when the observed data changes and the activity is in the foreground.
         */
        viewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable List<Word> wordList) {
                wordViewAdapter.setWords(wordList);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NewWordActivity.class);
                startActivityForResult(i, NEW_WORD_REQUEST_CODE);
            }
        });
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        wordViewAdapter = new WordViewAdapter();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(wordViewAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * If the activity returns with RESULT_OK, insert the returned word into the database by calling the insert()
         * method of the WordViewModel.
         */
        if (requestCode == NEW_WORD_REQUEST_CODE && resultCode == RESULT_OK){
            viewModel.insert(new Word(data.getStringExtra(NewWordActivity.EXTRA_WORD)));
        }
        else {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.empty_not_saved), Toast.LENGTH_SHORT).show();
        }

    }
}
