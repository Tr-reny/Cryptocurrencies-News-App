package com.tr_reny.cryptonews.Model;

import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("title")
    public String title;

    @SerializedName("link")
    public String link;

    @SerializedName("putDate")
    public String pubDate;

    @SerializedName("source")
    public String source;

    @SerializedName("guid")
    public String guid;


    //Getters

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getSource() {
        return source;
    }

    public String getGuid() {
        return guid;
    }
}
