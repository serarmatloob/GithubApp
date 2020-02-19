package com.matloob.githubapp.models;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

/**
 * Created by Serar Matloob on 2/18/2020.
 */
public class Converters {
    @TypeConverter
    public static String fromJson(Commit commit){
        return new Gson().toJson(commit);
    }

    @TypeConverter
    public static Commit toJson(String commitJson){
        return new Gson().fromJson(commitJson, Commit.class);
    }
}
