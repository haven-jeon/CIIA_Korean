package com.alag.ci.blog.impl;

public class BlogEntryTag {
    private Long tagId = null;
    private String tagText = null;
    
    public BlogEntryTag(Long tagId, String tagText) {
        super();
        this.tagId = tagId;
        this.tagText = tagText;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagText() {
        return tagText;
    }

    public void setTagText(String tagText) {
        this.tagText = tagText;
    }
    
    
    
}
