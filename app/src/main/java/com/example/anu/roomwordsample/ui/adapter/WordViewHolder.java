package com.example.anu.roomwordsample.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.anu.roomwordsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Design on 17-01-2018.
 */

public class WordViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textView)
    TextView textView;

    public WordViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
