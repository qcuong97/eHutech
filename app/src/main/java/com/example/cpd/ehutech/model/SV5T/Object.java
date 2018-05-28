package com.example.cpd.ehutech.model.SV5T;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Object {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("rows")
    @Expose
    private Row rows;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Row getRows() {
        return rows;
    }

    public void setRows(Row rows) {
        this.rows = rows;
    }
}
