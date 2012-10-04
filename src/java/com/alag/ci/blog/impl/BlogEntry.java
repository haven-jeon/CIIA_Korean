package com.alag.ci.blog.impl;

import java.net.URL;
import java.util.Calendar;
import java.util.List;

public class BlogEntry {
    private URL permalinkURL = null;
    private String body = null;
    private String title = null;
    private Long id = null;
    private Calendar postDate = null;
    private List<BlogEntryTag> tags = null;
    private List<BlogEntryComment> comments = null;
    private List<ReferenceWeblog> referenceWeblogs = null;
    
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public List<BlogEntryComment> getComments() {
        return comments;
    }
    public void setComments(List<BlogEntryComment> comments) {
        this.comments = comments;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public URL getPermalinkURL() {
        return permalinkURL;
    }
    public void setPermalinkURL(URL permalinkURL) {
        this.permalinkURL = permalinkURL;
    }
    public Calendar getPostDate() {
        return postDate;
    }
    public void setPostDate(Calendar postDate) {
        this.postDate = postDate;
    }
    public List<ReferenceWeblog> getReferenceWeblogs() {
        return referenceWeblogs;
    }
    public void setReferenceWeblogs(List<ReferenceWeblog> referenceWeblogs) {
        this.referenceWeblogs = referenceWeblogs;
    }
    public List<BlogEntryTag> getTags() {
        return tags;
    }
    public void setTags(List<BlogEntryTag> tags) {
        this.tags = tags;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    
}
