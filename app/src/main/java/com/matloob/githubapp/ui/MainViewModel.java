package com.matloob.githubapp.ui;

import androidx.lifecycle.ViewModel;

import com.matloob.githubapp.api.CommitsApi;

import javax.inject.Inject;

/**
 * Created by Serar Matloob on 2/2/2020.
 */
public class MainViewModel extends ViewModel {
    private CommitsApi commitsApi;

    @Inject
    public MainViewModel(CommitsApi commitsApi) {
        this.commitsApi = commitsApi;
    }
}
