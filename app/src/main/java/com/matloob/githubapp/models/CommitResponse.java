package com.matloob.githubapp.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Serar Matloob on 2/1/2020.
 */
@Entity(tableName = "commit_table")
public class CommitResponse implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "commit")
    @SerializedName("commit")
    @NonNull
    private Commit commit;

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public CommitResponse(Commit commit) {
        this.commit = commit;
    }
}
