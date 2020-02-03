package com.matloob.githubapp.dagger.main;

import com.matloob.githubapp.ui.SearchDialog;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Serar Matloob on 2/2/2020.
 */
@Module
public abstract class SearchFragmentModule {
    @ContributesAndroidInjector(
            modules = {MainActivityModule.class}
    )
    abstract SearchDialog contributeSearchDialog();
}
