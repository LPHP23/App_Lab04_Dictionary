package com.example.lab04_app_dictionary.model;

import java.util.List;

public class DictionaryResponse {

    public String word;
    public List<Meaning> meanings;

    public static class Meaning {
        public List<Definition> definitions;
    }

    public static class Definition {
        public String definition;
    }
}