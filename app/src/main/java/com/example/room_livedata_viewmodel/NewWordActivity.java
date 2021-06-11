package com.example.room_livedata_viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class NewWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
    }

    public void SaveNewWord(View view)
    {
        //TODO attach to insert method of database
        Intent backToMainActivity = new Intent();
        setResult(RESULT_OK);
        finish();
        //startActivity(backToMainActivity);
    }
}