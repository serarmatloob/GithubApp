package com.matloob.githubapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

/**
 * Created by Serar Matloob on 2/7/2020.
 */
public class SharedPreferencesUtil {

    public static final String PREF_REPO = "pref_repo";

    private Context context;

    public SharedPreferencesUtil(Context context) {
        this.context = context;
    }

    public <T> void saveObject(String key, Object object){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = gson.toJson(object);
        sharedPreferences.edit().putString(key, json).apply();
    }

    public <T> T getObject(String key, @NonNull Class<T> modelClass){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, null);

        return gson.fromJson(json, modelClass);

    }

}
