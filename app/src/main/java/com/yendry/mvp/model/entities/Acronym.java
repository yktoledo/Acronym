
package com.yendry.mvp.model.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Acronym {

    @SerializedName("sf")
    @Expose
    private String sf;
    @SerializedName("lfs")
    @Expose
    private List<Lf> lfs = null;

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf;
    }

    public List<Lf> getLfs() {
        return lfs;
    }

    public void setLfs(List<Lf> lfs) {
        this.lfs = lfs;
    }

}
