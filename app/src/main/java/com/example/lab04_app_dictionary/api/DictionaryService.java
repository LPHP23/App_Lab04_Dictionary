package com.example.lab04_app_dictionary.api;

import com.example.lab04_app_dictionary.model.DictionaryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DictionaryService {

    @GET("api/v2/entries/en/{word}")
    Call<List<DictionaryResponse>> getMeaning(@Path("word") String word);
}