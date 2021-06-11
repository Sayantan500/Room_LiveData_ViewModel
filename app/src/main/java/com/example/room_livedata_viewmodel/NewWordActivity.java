package com.example.room_livedata_viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewWordActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        editText = findViewById(R.id.NewWord);
    }

    public void SaveNewWord(View view)
    {
        Intent backToMainActivity = new Intent();
        String new_word;
        if(!TextUtils.isEmpty(editText.getText()))
        {
            new_word = editText.getText().toString();
            backToMainActivity.putExtra("new_word" , new_word);
            setResult(RESULT_OK,backToMainActivity);
            finish();
        }
        else
        {
            setResult(RESULT_CANCELED,backToMainActivity);
            finish();
        }
    }
}