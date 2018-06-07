package com.example.cpd.ehutech.model.ChuKy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Object {
    @SerializedName("chuky")
    @Expose
    private String chuky;

    public String getChuky() {
        return chuky;
    }

    public void setChuky(String chuky) {
        this.chuky = chuky;
    }
}
