package com.matloob.githubapp.ui;

import com.matloob.githubapp.models.CommitResponse;
import com.matloob.githubapp.models.GitRepository;

/**
 * Created by Serar Matloob on 2/2/2020.
 *
 * Callback interface for Main View Model.
 */
public interface MainViewListener {
    void onSetNewRepo(GitRepository repository);
    void onItemClick(CommitResponse commitResponse);
}
