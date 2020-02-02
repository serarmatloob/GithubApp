package com.matloob.githubapp.dagger;

import androidx.lifecycle.ViewModelProvider;

import com.matloob.githubapp.util.ViewModelFactory;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Serar Matloob on 2/2/2020.
 */
@Module
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);
}
