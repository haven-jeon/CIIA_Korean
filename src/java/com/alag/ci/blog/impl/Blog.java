package com.alag.ci.blog.impl;

import java.util.Calendar;
import java.util.List;

public class Blog {
    private Long userId = null;
    private Calendar dateStarted = null;
    private List<BlogEntry> blogEntries = null;
    
    public List<BlogEntry> getBlogEntries() {
        return blogEntries;
    }
    public void setBlogEntries(List<BlogEntry> blogEntries) {
        this.blogEntries = blogEntries;
    }
    public Calendar getDateStarted() {
        return dateStarted;
    }
    public void setDateStarted(Calendar dateStarted) {
        this.dateStarted = dateStarted;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
}
