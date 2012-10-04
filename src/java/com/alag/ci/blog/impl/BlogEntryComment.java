package com.alag.ci.blog.impl;

import java.net.URL;
import java.util.Calendar;

public class BlogEntryComment {
    private String name = null;
    private URL commentURL = null;
    private String emailAddress = null;
    private Calendar timeStamp = null;
    
    public BlogEntryComment(String name, URL commentURL, String emailAddress) {
        super();
        this.name = name;
        this.commentURL = commentURL;
        this.emailAddress = emailAddress;
    }

    public URL getCommentURL() {
        return commentURL;
    }

    public void setCommentURL(URL commentURL) {
        this.commentURL = commentURL;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Calendar timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    
    
}
