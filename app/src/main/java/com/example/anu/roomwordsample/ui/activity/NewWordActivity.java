package com.example.anu.roomwordsample.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.example.anu.roomwordsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewWordActivity extends AppCompatActivity {

    @BindView(R.id.edit_word)
    EditText editWord;
    @BindView(R.id.button_save)
    Button buttonSave;

    static final String EXTRA_WORD = "word";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_save)
    public void onViewClicked() {
        Intent i = new Intent();
        if (TextUtils.isEmpty(editWord.getText().toString())){
            setResult(RESULT_CANCELED, i);
        }else {
            i.putExtra(EXTRA_WORD, editWord.getText().toString());
            setResult(RESULT_OK, i);
        }
        finish();
    }
}
