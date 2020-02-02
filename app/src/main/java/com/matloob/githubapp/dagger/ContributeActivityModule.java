package com.matloob.githubapp.dagger;

import com.matloob.githubapp.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Serar Matloob on 2/1/2020.
 */
@Module
public abstract class ContributeActivityModule {

    @ContributesAndroidInjector()
    abstract MainActivity contributeMainActivity();
}