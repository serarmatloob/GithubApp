package com.matloob.githubapp.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CommitResponseDao {

    @Query("SELECT * from commit_table")
    LiveData<List<CommitResponse>> getCommits();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CommitResponse commitResponse);

    @Query("DELETE FROM commit_table")
    void deleteAll();
}
