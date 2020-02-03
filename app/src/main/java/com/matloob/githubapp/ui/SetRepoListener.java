package com.matloob.githubapp.ui;

/**
 * Created by Serar Matloob on 2/2/2020.
 *
 * Callback interface fired when new repo is set.
 */
public interface SetRepoListener {
    void onSetNewRepo(String owner, String repo);
}
