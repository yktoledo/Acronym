package com.yendry.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.yendry.mvp.model.Repository;
import com.yendry.mvp.model.entities.Acronym;
import com.yendry.mvp.model.entities.Lf;
import com.yendry.mvp.view.MainView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by User on 11/22/2016.
 */

public class MainPresenter implements MainMvpPresenter {
    private static final String TAG = MainPresenter.class.getSimpleName();
    MainView mainView;
    Repository repository;
    private Context context;

    @Inject
    public MainPresenter(Repository repository, Context context) {
        this.repository = repository;
        this.context = context;
    }


    @Override
    public void attachView(MainView view) {
        this.mainView=view;
    }

    @Override
    public void detachView() {
        mainView = null;
    }

    @Override
    public void getAcron(String str) {
        mainView.showProgress();
        Map<String, String> opt = new HashMap<>();
        opt.put("sf", str);
        repository.getAcronym(opt)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Acronym>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.showText("Nothing to show");
                        mainView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Acronym> acronyms) {
                        mainView.hideProgress();
                        List<String> list = new ArrayList<>();
                        if (acronyms !=null && acronyms.size()>0){
                            if (acronyms.get(0).getLfs() != null && acronyms.get(0).getLfs().size()>0){
                                for (Lf item : acronyms.get(0).getLfs()) {
                                    list.add(item.getLf());
                                }
                            }

                        }else {
                            mainView.showError("Nothing to show");
                        }
                        mainView.showData(list);
                    }
                });
    }
}
