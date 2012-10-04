package com.alag.ci.contenttype;

import java.util.List;

public class CompositeContentType implements ContentType {
    private List<ContentType> contentTypes = null;

    public List<ContentType> getContentTypes() {
        return contentTypes;
    }

    public void setContentTypes(List<ContentType> contentTypes) {
        this.contentTypes = contentTypes;
    }
    
    
    
}
