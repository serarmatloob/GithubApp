package com.matloob.githubapp.dagger.main;

import com.matloob.githubapp.api.CommitsApi;

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
}
