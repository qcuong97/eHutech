package com.example.cpd.ehutech.model.ChuKy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadChuKy {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("results")
    @Expose
    private Results results;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
