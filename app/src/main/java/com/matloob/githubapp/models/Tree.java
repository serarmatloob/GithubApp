package com.matloob.githubapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Serar Matloob on 2/1/2020.
 */
public class Tree implements Serializable {
    @SerializedName("sha")
    private String sha;

    public String getSha() {
        return sha;
    }
}
