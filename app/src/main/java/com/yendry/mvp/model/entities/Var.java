
package com.yendry.mvp.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Var {

    @SerializedName("lf")
    @Expose
    private String lf;
    @SerializedName("freq")
    @Expose
    private Integer freq;
    @SerializedName("since")
    @Expose
    private Integer since;

    public String getLf() {
        return lf;
    }

    public void setLf(String lf) {
        this.lf = lf;
    }

    public Integer getFreq() {
        return freq;
    }

    public void setFreq(Integer freq) {
        this.freq = freq;
    }

    public Integer getSince() {
        return since;
    }

    public void setSince(Integer since) {
        this.since = since;
    }

}
