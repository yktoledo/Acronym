package com.yendry.mvp.model;

import com.yendry.mvp.model.model.Acronym;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface ApiCall {

//http://www.nactem.ac.uk/software/acromine/dictionary.py?sf=HMM

    @GET("/software/acromine/dictionary.py")
    Observable<List<Acronym>> getAcro(@QueryMap Map<String, String> options);
}
