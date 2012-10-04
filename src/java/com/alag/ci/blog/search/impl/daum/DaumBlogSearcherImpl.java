package com.alag.ci.blog.search.impl.daum;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;


import com.alag.ci.blog.search.BlogQueryParameter;
import com.alag.ci.blog.search.BlogSearchResponseHandler;
import com.alag.ci.blog.search.BlogSearcherException;
import com.alag.ci.blog.search.BlogQueryParameter.QueryParameter;
import com.alag.ci.blog.search.impl.BlogSearcherImpl;


/*
 * http://apis.daum.net/search/blog?q=ipad&result=10&sort=accu&output=xml&apikey=85351b0cd53b3298af43878c1472fd6792626c5c
 */
public class DaumBlogSearcherImpl extends BlogSearcherImpl {

	public DaumBlogSearcherImpl() throws BlogSearcherException {
		// TODO Auto-generated constructor stub
	}

	protected BlogSearchResponseHandler getBlogSearchResponseHandler() { 
		return new DaumResponseHandler();
	}
	@Override
	protected HttpMethod getMethod(BlogQueryParameter param) {
		String url = param.getMethodUrl() + "?" +
				"q=" + urlEncode(param.getParameter(QueryParameter.QUERY)) +
				"&result=" + param.getParameter(QueryParameter.LIMIT) + 
				"&sort=" + param.getParameter(QueryParameter.SORTBY) +
				"&output=xml" + 
				"&apikey=" + param.getParameter(QueryParameter.KEY);
		System.out.println(url);
		GetMethod method = new GetMethod(url);
        return method;
	}

}
