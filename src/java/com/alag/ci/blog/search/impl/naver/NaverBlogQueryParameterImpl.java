package com.alag.ci.blog.search.impl.naver;

import com.alag.ci.blog.search.impl.BlogQueryParameterImpl;

public class NaverBlogQueryParameterImpl extends BlogQueryParameterImpl {
	private static final String  NAVER_SEARCH_API_URL =
        "http://openapi.naver.com/search";  
    
    public NaverBlogQueryParameterImpl() {
        super(QueryType.SEARCH,NAVER_SEARCH_API_URL);
    }
}
