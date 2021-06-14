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

public class recyclerViewAdapter extends ListAdapter<Word_Entity , recyclerViewAdapter.wordViewHolder>
{
    recyclerViewAdapter(@NonNull DiffUtil.ItemCallback<Word_Entity> callback)
    {
        super(callback);
    }

    static class WordDiff extends DiffUtil.ItemCallback<Word_Entity>
    {

        @Override
        public boolean areItemsTheSame(@NonNull Word_Entity oldItem, @NonNull Word_Entity newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Word_Entity oldItem, @NonNull Word_Entity newItem) {
            return oldItem.getWord().equals(newItem.getWord());
        }
    }

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
    }

    Word_Entity getWordEntity(int position)
    {
        return getItem(position);
    }

    protected static class wordViewHolder extends RecyclerView.ViewHolder
    {
        final TextView textView;
        public wordViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recyclerTextView);
        }
    }
}
