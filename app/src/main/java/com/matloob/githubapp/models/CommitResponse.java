package com.matloob.githubapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Serar Matloob on 2/1/2020.
 */
public class CommitResponse implements Serializable {
    @SerializedName("commit")
    private Commit commit;

    public Commit getCommit() {
        return commit;
    }
}
