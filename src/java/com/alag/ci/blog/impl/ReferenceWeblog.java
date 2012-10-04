package com.alag.ci.blog.impl;

import java.net.URL;
import java.util.Calendar;

public class ReferenceWeblog {
    private String title = null;
    private URL url = null;
    private Calendar trackTime = null;
    
    public ReferenceWeblog(String title, URL url, Calendar trackTime) {
        super();
        this.title = title;
        this.url = url;
        this.trackTime = trackTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Calendar getTrackTime() {
        return trackTime;
    }

    public void setTrackTime(Calendar trackTime) {
        this.trackTime = trackTime;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
    
    
    
}
