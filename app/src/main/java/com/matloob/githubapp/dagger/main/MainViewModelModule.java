package com.matloob.githubapp.dagger.main;

import androidx.lifecycle.ViewModel;

import com.matloob.githubapp.dagger.ViewModelKey;
import com.matloob.githubapp.ui.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Serar Matloob on 2/2/2020.
 */
@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);
}
