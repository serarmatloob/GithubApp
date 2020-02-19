package com.matloob.githubapp.dagger.main;

import com.matloob.githubapp.api.CommitsApi;
import com.matloob.githubapp.repository.Repository;
import com.matloob.githubapp.ui.CommitsRecyclerAdapter;
import com.matloob.githubapp.ui.MainActivity;
import com.matloob.githubapp.ui.MainViewListener;
import com.matloob.githubapp.util.SharedPreferencesUtil;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Serar Matloob on 2/1/2020.
 */
@Module
public abstract class MainActivityModule {
    @Provides
    static CommitsApi provideCommitsApi(Retrofit retrofit) {
        return retrofit.create(CommitsApi.class);
    }

    @Provides
    static CommitsRecyclerAdapter provideCommitAdapter(MainViewListener mainViewListener) {
        return new CommitsRecyclerAdapter(mainViewListener);
    }

    @Provides
    static MainViewListener provideMainViewListener(MainActivity mainActivity) {
        return mainActivity;
    }

    @Provides
    static Repository provideRepo(SharedPreferencesUtil sharedPreferencesUtil) {
        return new Repository(sharedPreferencesUtil);
    }
}
