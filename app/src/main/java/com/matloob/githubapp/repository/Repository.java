package com.matloob.githubapp.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.matloob.githubapp.BaseApplication;
import com.matloob.githubapp.models.CommitResponse;
import com.matloob.githubapp.models.CommitResponseDao;
import com.matloob.githubapp.models.CommitRoomDatabase;
import com.matloob.githubapp.models.GitRepository;
import com.matloob.githubapp.util.SharedPreferencesUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Serar Matloob on 2/7/2020.
 */
public class Repository {
    private static final String TAG = "Repository";
    private CommitResponseDao mCommitDao;
    private LiveData<List<CommitResponse>> mAllCommits;

    private SharedPreferencesUtil sharedPreferencesUtil;

    @Inject
    public Repository(SharedPreferencesUtil sharedPreferencesUtil, Application application) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
        if(application != null){
            Log.i(TAG, "application != null");
            CommitRoomDatabase db = CommitRoomDatabase.getDatabase(application);
            mCommitDao = db.commitResponseDao();
            mAllCommits = mCommitDao.getCommits();
        }

    }

    public LiveData<List<CommitResponse>> getmAllCommits() {
        return mAllCommits;
    }

    public GitRepository getGitRepository() {
        return sharedPreferencesUtil.getObject(SharedPreferencesUtil.PREF_REPO, GitRepository.class);
    }

    public void setGitRepository(GitRepository gitRepository) {
        sharedPreferencesUtil.saveObject(SharedPreferencesUtil.PREF_REPO, gitRepository);
    }

    public void insert(CommitResponse commitResponse) {
        CommitRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCommitDao.insert(commitResponse);
            Log.i(TAG, "inserted: ");
        });
    }

    public void removeAll(){
        CommitRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCommitDao.deleteAll();
            Log.i(TAG, "removed: ");
        });
    }
}
