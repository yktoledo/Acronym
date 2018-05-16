package com.yendry.mvp.model;

import com.yendry.mvp.model.entities.Acronym;

import java.util.List;
import java.util.Map;

import rx.Observable;

public class Repository {


    private ApiCall service;

    public Repository(ApiCall service) {

        this.service = service;
    }

    public Observable<List<Acronym>> getAcronym(Map<String, String> opt){
        return service.getAcro(opt);
    }
}
