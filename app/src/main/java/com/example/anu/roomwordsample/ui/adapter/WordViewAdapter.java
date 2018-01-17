package com.example.anu.roomwordsample.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anu.roomwordsample.R;
import com.example.anu.roomwordsample.data.Word;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Design on 17-01-2018.
 */

public class WordViewAdapter extends RecyclerView.Adapter<WordViewHolder> {

    private List<Word> wordList;

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (null == wordList)
            holder.textView.setText("No word");
        else{
            Word word = wordList.get(position);
            holder.textView.setText(word.getmWord());
        }
    }

    @Override
    public int getItemCount() {
        if (null != wordList)
            return wordList.size();
        else
            return 0;
    }

    public void setWords(List<Word> wordList){
        this.wordList = wordList;
        notifyDataSetChanged();
    }
}
