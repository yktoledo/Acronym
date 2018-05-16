package com.yendry.mvp.di;

import com.yendry.mvp.presenter.MainPresenter;
import com.yendry.mvp.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MainActivity activity);

}
