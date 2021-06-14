package com.example.room_livedata_viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    WordViewModel wordViewModel;

    recyclerViewAdapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        recyclerViewAdapter = new recyclerViewAdapter(new recyclerViewAdapter.WordDiff());
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

        wordViewModel.getAllWords().observe(this, word_entities -> recyclerViewAdapter.submitList(word_entities));

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        floatingActionButton.setOnClickListener((View v) -> {
            Intent newWordActivity = new Intent(MainActivity.this,NewWordActivity.class);
            startActivityForResult(newWordActivity , REQUEST_CODE);
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                wordViewModel.delete(
                        recyclerViewAdapter.getWordEntity(
                                viewHolder.getAdapterPosition()
                        )
                );

                Toast.makeText(getApplicationContext() ,
                        "Deleted..." ,
                        Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                Word_Entity word_entity = new Word_Entity(data.getStringExtra("new_word"));
                wordViewModel.insert(word_entity);
                Toast.makeText(MainActivity.this , "Word Added Successfully" , Toast.LENGTH_LONG).show();
            }
            else if(resultCode == RESULT_CANCELED)
                Toast.makeText(MainActivity.this , "Word Already Present" , Toast.LENGTH_SHORT).show();
        }
    }
}