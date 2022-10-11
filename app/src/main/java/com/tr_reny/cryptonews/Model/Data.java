package com.tr_reny.cryptonews.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("guid")
    @Expose
    public String guid;
    @SerializedName("published_on")
    @Expose
    public Integer publishedOn;
    @SerializedName("imageurl")
    @Expose
    public String imageurl;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("source")
    @Expose
    public String source;
    @SerializedName("body")
    @Expose
    public String body;
    @SerializedName("tags")
    @Expose
    public String tags;
    @SerializedName("categories")
    @Expose
    public String categories;
    @SerializedName("upvotes")
    @Expose
    public String upvotes;
    @SerializedName("downvotes")
    @Expose
    public String downvotes;
    @SerializedName("lang")
    @Expose
    public String lang;
    @SerializedName("source_info")
    @Expose
    public SourceInfo sourceInfo;

    //Getters
    public String getId() {
        return id;
    }

    public String getGuid() {
        return guid;
    }

    public Integer getPublishedOn() {
        return publishedOn;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getSource() {
        return source;
    }

    public String getBody() {
        return body;
    }

    public String getTags() {
        return tags;
    }

    public String getCategories() {
        return categories;
    }

    public String getUpvotes() {
        return upvotes;
    }

    public String getDownvotes() {
        return downvotes;
    }

    public String getLang() {
        return lang;
    }

    public SourceInfo getSourceInfo() {
        return sourceInfo;
    }
}