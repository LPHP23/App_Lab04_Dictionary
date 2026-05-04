package com.example.lab04_app_dictionary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab04_app_dictionary.R;
import com.example.lab04_app_dictionary.model.DictionaryResponse;

import java.util.List;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.ViewHolder> {

    private List<DictionaryResponse> list;

    public DictionaryAdapter(List<DictionaryResponse> list) {
        this.list = list;
    }

    public void setData(List<DictionaryResponse> newList) {
        this.list = newList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView word, meaning;

        ViewHolder(View v) {
            super(v);
            word = v.findViewById(R.id.txtWord);
            meaning = v.findViewById(R.id.txtMeaning);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_word, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DictionaryResponse item = list.get(position);

        holder.word.setText(item.word);

        if (item.meanings != null && !item.meanings.isEmpty()) {
            holder.meaning.setText(
                    item.meanings.get(0).definitions.get(0).definition
            );
        } else {
            holder.meaning.setText("No meaning");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}