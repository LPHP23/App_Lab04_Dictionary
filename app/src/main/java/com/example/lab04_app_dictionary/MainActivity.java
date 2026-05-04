package com.example.lab04_app_dictionary;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab04_app_dictionary.adapter.DictionaryAdapter;
import com.example.lab04_app_dictionary.api.DictionaryService;
import com.example.lab04_app_dictionary.model.DictionaryResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText edtSearch;
    RecyclerView recycler;
    DictionaryAdapter adapter;
    DictionaryService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSearch = findViewById(R.id.edtSearch);
        recycler = findViewById(R.id.recyclerView);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DictionaryAdapter(new ArrayList<>());
        recycler.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.dictionaryapi.dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(DictionaryService.class);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s,int a,int b,int c){}
            @Override public void afterTextChanged(Editable s){}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 2) {
                    searchWord(s.toString());
                }
            }
        });
    }

    private void searchWord(String word) {

        api.getMeaning(word).enqueue(new Callback<List<DictionaryResponse>>() {
            @Override
            public void onResponse(Call<List<DictionaryResponse>> call,
                                   Response<List<DictionaryResponse>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    adapter.setData(response.body());
                } else {
                    adapter.setData(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<DictionaryResponse>> call, Throwable t) {
                t.printStackTrace();
                adapter.setData(new ArrayList<>());
            }
        });
    }
}