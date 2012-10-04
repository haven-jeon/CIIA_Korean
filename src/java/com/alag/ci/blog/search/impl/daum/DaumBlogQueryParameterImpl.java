package com.alag.ci.blog.search.impl.daum;

import com.alag.ci.blog.search.impl.BlogQueryParameterImpl;

public class DaumBlogQueryParameterImpl extends BlogQueryParameterImpl {
	private static final String  DAUM_SEARCH_API_URL =
        "http://apis.daum.net/search/blog";  
    
    public DaumBlogQueryParameterImpl() {
        super(QueryType.SEARCH,DAUM_SEARCH_API_URL);
    }
}
