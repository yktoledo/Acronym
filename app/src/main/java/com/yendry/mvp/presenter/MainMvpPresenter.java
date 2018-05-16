package com.yendry.mvp.presenter;

import com.yendry.mvp.BasePresenter;
import com.yendry.mvp.view.MainView;

/**
 * Created by User on 11/22/2016.
 */

public interface MainMvpPresenter extends BasePresenter<MainView> {
    void getAcron(String str);
}
