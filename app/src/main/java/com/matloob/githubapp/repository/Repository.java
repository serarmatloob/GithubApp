package com.matloob.githubapp.repository;

import com.matloob.githubapp.models.GitRepository;
import com.matloob.githubapp.util.SharedPreferencesUtil;

import javax.inject.Inject;

/**
 * Created by Serar Matloob on 2/7/2020.
 */
public class Repository {

    private SharedPreferencesUtil sharedPreferencesUtil;

    @Inject
    public Repository(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }


    public GitRepository getGitRepository() {
        return sharedPreferencesUtil.getObject(SharedPreferencesUtil.PREF_REPO, GitRepository.class);
    }

    public void setGitRepository(GitRepository gitRepository) {
        sharedPreferencesUtil.saveObject(SharedPreferencesUtil.PREF_REPO, gitRepository);
    }
}
