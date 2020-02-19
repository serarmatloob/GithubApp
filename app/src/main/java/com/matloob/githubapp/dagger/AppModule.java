package com.matloob.githubapp.dagger;

import android.app.Application;

import com.matloob.githubapp.util.Constants;
import com.matloob.githubapp.util.SharedPreferencesUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Serar Matloob on 2/1/2020.
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static SharedPreferencesUtil providePreferencesUtil(Application context) {
        return new SharedPreferencesUtil(context.getApplicationContext());
    }

}
