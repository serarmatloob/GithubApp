package com.matloob.githubapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Serar Matloob on 2/1/2020.
 */
public class CommitAuthor implements Serializable {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
