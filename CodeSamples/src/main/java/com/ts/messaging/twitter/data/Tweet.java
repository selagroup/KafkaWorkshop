package com.ts.messaging.twitter.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jayway.jsonpath.JsonPath;
import twitter4j.Status;

import java.util.Date;
import java.util.UUID;
import java.util.function.Predicate;

public class Tweet implements Identity {

    private static final JsonPath TWEET_PARSER = JsonPath.compile("$");


    @JsonProperty
    private String id;
    private int favoriteCount;
    private int retweetCount;
    private String text;
    private Date createdAt;
    private String language;
    private String source;
    private String rawTweetJson;
    private long inReponseTo;

    private long tweet_id;



    public long getInReponseTo() {
        return inReponseTo;
    }
    public void setInReponseTo(long inReponseTo) {
        this.inReponseTo = inReponseTo;
    }


    protected String createUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    public Tweet() {
    }

    public Tweet(String id, Status status) {
        this.id = id;
        this.favoriteCount = status.getFavoriteCount();
        this.retweetCount = status.getRetweetCount();
        this.text = status.getText();
        this.createdAt = status.getCreatedAt();
        this.language = status.getLang();
        this.source = status.getSource();
        this.inReponseTo = status.getInReplyToStatusId();
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }
    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }
    public int getRetweetCount() {
        return retweetCount;
    }
    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }


    @Override
    public String toString() {
        return "Tweet{" +
                "id='" + id + '\'' +
                ", favoriteCount=" + favoriteCount +
                ", retweetCount=" + retweetCount +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", language='" + language + '\'' +
                ", source='" + source + '\'' +
                '}';
    }

    @JsonIgnore
    public String getKey() {
        return id;
    }

    public static Predicate<Tweet> iphoneSource() {
        return tweet -> tweet.getSource().contains("iPhone");
    }
    public static Predicate<Tweet> androidSource() {
        return tweet -> tweet.getSource().contains("Android");
    }
    public Predicate<Tweet> ipadSource() {
        return tweet -> tweet.getSource().contains("Ipad");
    }
    public Predicate<Tweet> webSource() {
        return tweet -> tweet.getSource().contains("Web");
    }

    @JsonIgnore
    public String deviceFromSource() {
        if (iphoneSource().test(this)) {
            return "iPhone";
        } else if (androidSource().test(this)) {
            return "Android";
        } else if (ipadSource().test(this)) {
            return "Ipad";
        } else if (webSource().test(this)) {
            return "Web";
        }
        return "else";

    }
}
