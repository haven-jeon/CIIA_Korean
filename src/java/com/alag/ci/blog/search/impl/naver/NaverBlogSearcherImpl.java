package com.alag.ci.blog.search.impl.naver;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.alag.ci.blog.search.BlogQueryParameter;
import com.alag.ci.blog.search.BlogSearchResponseHandler;
import com.alag.ci.blog.search.BlogSearcherException;
import com.alag.ci.blog.search.BlogQueryParameter.QueryParameter;
import com.alag.ci.blog.search.impl.BlogSearcherImpl;


/**
 * 
 * @author heewon
 * 리뷰'를 검색할 경우 :
 * http://openapi.naver.com/search?key=test&query=%EB%A6%AC%EB%B7%B0&display=10&start=1&target=blog&sort=sim
 *
 */
public class NaverBlogSearcherImpl extends BlogSearcherImpl {

	public NaverBlogSearcherImpl() throws BlogSearcherException {
		
	}
	protected BlogSearchResponseHandler getBlogSearchResponseHandler() { 
		return new NaverResponseHandler();
	}
	@Override
	protected HttpMethod getMethod(BlogQueryParameter param) {
		// TODO Auto-generated method stub
		String url = param.getMethodUrl() + "?" + 
			"key=" + param.getParameter(QueryParameter.KEY) + 
			"&query=" + urlEncode(param.getParameter(QueryParameter.QUERY)) + 
			"&display=" + param.getParameter(QueryParameter.LIMIT) + 
			"&start=" + param.getParameter(QueryParameter.START_INDEX) + 
			"&target=blog" + 
			"&sort=" + param.getParameter(QueryParameter.SORTBY);
			
		System.out.println(url);
		GetMethod method = new GetMethod(url);
        return method;
	}

}
