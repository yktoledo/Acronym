package com.yendry.mvp.view;

import com.yendry.mvp.BaseView;

import java.util.List;

/**
 * Created by User on 11/22/2016.
 */

public interface MainView extends BaseView {
    void showText(String text);

    void showData(List<String> list);
}
