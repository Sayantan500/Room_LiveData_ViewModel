package com.example.room_livedata_viewmodel;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditWordActivity extends AppCompatActivity
{

    TextView textView;
    EditText editText;
    Button editSaveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_word);

        textView = findViewById(R.id.textView3);
        editText = findViewById(R.id.editText);
        editSaveButton = findViewById(R.id.button_edit_save);

        textView.setText(String.valueOf(getIntent().getIntExtra("old_word_pk",1)));

        String oldWord = getIntent().getStringExtra("old_word_to_edit");
        editText.setText(oldWord);
        editText.setTextColor(Color.DKGRAY);

        editSaveButton.setOnClickListener(v -> {
            Intent backToMainActivity = new Intent();
            if(!TextUtils.isEmpty(editText.getText()))
            {
                backToMainActivity.putExtra("New_word_edited" , editText.getText().toString());
                backToMainActivity.putExtra("key" , getIntent().getIntExtra("old_word_pk",0));
                setResult(RESULT_OK , backToMainActivity);
            }
            else
                setResult(RESULT_CANCELED , backToMainActivity);
            finish();
        });
    }
}