package com.matloob.githubapp.dagger;

import android.app.Application;

import com.matloob.githubapp.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Serar Matloob on 2/1/2020.
 */
@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ContributeActivityModule.class,
                AppModule.class
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
