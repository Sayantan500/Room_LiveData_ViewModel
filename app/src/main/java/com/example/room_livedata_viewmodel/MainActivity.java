package com.example.room_livedata_viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.floatingActionButton);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        floatingActionButton.setOnClickListener((View v) -> {
            Intent newWordActivity = new Intent(MainActivity.this,NewWordActivity.class);
            startActivityForResult(newWordActivity , REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
                Toast.makeText(MainActivity.this , "Word Added Successfully" , Toast.LENGTH_LONG).show();
            else if(resultCode == RESULT_CANCELED)
                Toast.makeText(MainActivity.this , "Word Already Present" , Toast.LENGTH_SHORT).show();
        }
    }
}