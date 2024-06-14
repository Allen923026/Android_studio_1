package com.example.finalproject3up;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class DictionaryManager {
    private static final String DICTIONARY_PREFS = "dictionary_prefs";

    // 保存字典的方法
    public static void saveDictionary(Context context, Map<String, String> dictionary) {
        SharedPreferences.Editor editor = context.getSharedPreferences(DICTIONARY_PREFS, Context.MODE_PRIVATE).edit();

        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            editor.putString(entry.getKey(), entry.getValue());
        }

        editor.apply();
    }

    // 讀取字典的方法
    public static Map<String, String> loadDictionary(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(DICTIONARY_PREFS, Context.MODE_PRIVATE);
        Map<String, String> dictionary = new HashMap<>();

        for (Map.Entry<String, ?> entry : prefs.getAll().entrySet()) {
            if (entry.getValue() instanceof String) {
                dictionary.put(entry.getKey(), (String) entry.getValue());
            }
        }

        return dictionary;
    }
}
