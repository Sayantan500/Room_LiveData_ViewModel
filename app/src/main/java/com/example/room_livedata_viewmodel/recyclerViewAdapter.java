package com.example.room_livedata_viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class recyclerViewAdapter extends ListAdapter<Word_Entity , recyclerViewAdapter.wordViewHolder>
{
    private static EditTextCallback listener;
    recyclerViewAdapter()
    {
        super(WordDiffCallback);
    }

    private static final DiffUtil.ItemCallback<Word_Entity>
            WordDiffCallback = new DiffUtil.ItemCallback<Word_Entity>() {
        @Override
        public boolean areItemsTheSame(@NonNull Word_Entity oldItem, @NonNull Word_Entity newItem) {
            return oldItem.getWord() == newItem.getWord();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Word_Entity oldItem, @NonNull Word_Entity newItem) {
            return oldItem.getWord().equals(newItem.getWord());
        }
    };

    @NonNull
    @Override
    public wordViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View myOwnView = inflater.inflate(R.layout.recycler_item , parent , false);
        return new wordViewHolder(myOwnView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull recyclerViewAdapter.wordViewHolder holder, int position)
    {
        Word_Entity newWordEntity = getItem(position);
        holder.textView.setText(newWordEntity.getWord());
        holder.textView_pk. setText(String.valueOf(newWordEntity.getWordNo()));
        holder.textView.setOnClickListener(v -> listener.onClick(newWordEntity));
    }

    Word_Entity getWordEntity(int position)
    {
        return getItem(position);
    }

    public interface EditTextCallback
    {
        void onClick(Word_Entity word);
    }

    void setOnClickListener(EditTextCallback listener)
    {
        recyclerViewAdapter.listener = listener;
    }

    protected static class wordViewHolder extends RecyclerView.ViewHolder
    {
        final TextView textView,textView_pk;
        public wordViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recyclerTextView);
            textView_pk = itemView.findViewById(R.id.textView_pk);
        }
    }
}
