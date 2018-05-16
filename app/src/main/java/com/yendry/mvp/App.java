package com.yendry.mvp;

import android.app.Application;

import com.yendry.mvp.di.ApplicationComponent;
import com.yendry.mvp.di.ApplicationModule;
import com.yendry.mvp.di.DaggerApplicationComponent;

public class App extends Application {
    private final String baseUrl = "http://www.nactem.ac.uk/";
    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, baseUrl ))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
