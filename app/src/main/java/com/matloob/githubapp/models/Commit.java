package com.matloob.githubapp.models;

import androidx.room.Embedded;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Serar Matloob on 2/1/2020.
 */
public class Commit implements Serializable {

    @SerializedName("author")
    @Embedded
    private CommitAuthor author;
    @SerializedName("message")
    private String message;
    @SerializedName("tree")
    @Embedded
    private Tree tree;



    public CommitAuthor getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public Tree getTree() {
        return tree;
    }
}
