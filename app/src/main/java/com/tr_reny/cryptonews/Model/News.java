package com.tr_reny.cryptonews.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {

    @SerializedName("Type")
    @Expose
    public Integer type;
    @SerializedName("Message")
    @Expose
    public String message;
    @SerializedName("Promoted")
    @Expose
    public List<Object> promoted = null;
    @SerializedName("RateLimit")
    @Expose
    public RateLimit rateLimit;
    @SerializedName("HasWarning")
    @Expose
    public Boolean hasWarning;
    @SerializedName("Data")
    @Expose
    private Data[] data = null;

    //Constructor


    //Getters and Setters

    public Integer getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public List<Object> getPromoted() {
        return promoted;
    }

    public Data[] getData() {
        return data;
    }

    public RateLimit getRateLimit() {
        return rateLimit;
    }

    public Boolean getHasWarning() {
        return hasWarning;
    }
}