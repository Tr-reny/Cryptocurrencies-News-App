package com.tr_reny.cryptonews.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SourceInfo {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("lang")
    @Expose
    public String lang;
    @SerializedName("img")
    @Expose
    public String img;

    //Getters

    public String getName() {
        return name;
    }

    public String getLang() {
        return lang;
    }

    public String getImg() {
        return img;
    }
}