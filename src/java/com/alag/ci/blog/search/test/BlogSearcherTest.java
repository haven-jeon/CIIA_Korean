package com.alag.ci.blog.search.test;

import com.alag.ci.blog.search.BlogQueryParameter;
import com.alag.ci.blog.search.BlogQueryResult;
import com.alag.ci.blog.search.BlogSearcher;
import com.alag.ci.blog.search.BlogQueryParameter.QueryParameter;
import com.alag.ci.blog.search.impl.bloglines.BlogLineSearchBlogQueryParameterImpl;
import com.alag.ci.blog.search.impl.bloglines.BlogLinesBlogSearcherImpl;
import com.alag.ci.blog.search.impl.daum.DaumBlogQueryParameterImpl;
import com.alag.ci.blog.search.impl.daum.DaumBlogSearcherImpl;
import com.alag.ci.blog.search.impl.rss.RSSFeedBlogQueryParameterImpl;
import com.alag.ci.blog.search.impl.rss.RSSFeedBlogSearcherImpl;
import com.alag.ci.blog.search.impl.rss.RSSFeedBlogQueryParameterImpl.RSSProviderURL;
import com.alag.ci.blog.search.impl.technorati.TechnoratiBlogSearcherImpl;
import com.alag.ci.blog.search.impl.technorati.TechnoratiSearchBlogQueryParameterImpl;
import com.alag.ci.blog.search.impl.technorati.TechnoratiTagBlogQueryParameterImpl;
import com.alag.ci.blog.search.impl.naver.*;


import junit.framework.TestCase;

public class BlogSearcherTest extends TestCase {

    public void testTechnoratiTagBlogSearch() throws Exception {
        BlogSearcher bs = new TechnoratiBlogSearcherImpl();

        BlogQueryParameter tagQueryParam = 
            new TechnoratiTagBlogQueryParameterImpl();
 //           new RSSFeedBlogQueryParameterImpl(RSSProviderURL.TECHNORATI_TAG_SEARCH);
        tagQueryParam.setParameter(QueryParameter.KEY,
                "aa2d0294b0b14fff42f68b81d399d0e1");
        tagQueryParam.setParameter(QueryParameter.LIMIT, "200");
        tagQueryParam.setParameter(QueryParameter.TAG,
                "collective intelligence");
        tagQueryParam.setParameter(QueryParameter.LANGUAGE, "en");  
        
        BlogQueryResult tagResult = bs.getRelevantBlogs(tagQueryParam);
        System.out.println(tagResult);
        assertTrue("No results", tagResult.getRelevantBlogs().size() > 0);
   
    
    }
    
    public void testTechnoratiSearchQuery() throws Exception {
        BlogSearcher bs = new TechnoratiBlogSearcherImpl();
        BlogQueryParameter searchQueryParam =
            new TechnoratiSearchBlogQueryParameterImpl();
     //       new RSSFeedBlogQueryParameterImpl(RSSProviderURL.TECHNORATI_SEARCH);
        searchQueryParam.setParameter(QueryParameter.KEY,
                "aa2d0294b0b14fff42f68b81d399d0e1");
        searchQueryParam.setParameter(QueryParameter.LIMIT, "1");
        searchQueryParam.setParameter(QueryParameter.QUERY,
                "collective intelligence");
       // searchQueryParam.setParameter(QueryParameter.LANGUAGE, "en");  
        
        BlogQueryResult searchResult = bs.getRelevantBlogs(searchQueryParam);
        System.out.println(searchResult);
//        assertTrue("No results", searchResult.getRelevantBlogs().size() > 0);
    }
    
    public void testBloglinesBlogSearch() throws Exception {
        BlogSearcher bs = new BlogLinesBlogSearcherImpl();
        
        BlogQueryParameter searchQueryParam =
      //      new RSSFeedBlogQueryParameterImpl(RSSProviderURL.BLOGLINE);    
            new BlogLineSearchBlogQueryParameterImpl();
        //searchQueryParam.setParameter(QueryParameter.APIUSER, "satnamalag@yahoo.com");
        //searchQueryParam.setParameter(QueryParameter.KEY, "87A16A615AE7215940427C93484F97B0");
        searchQueryParam.setParameter(QueryParameter.APIUSER, "madjakarta@gmail.com");
        searchQueryParam.setParameter(QueryParameter.KEY, "182B5926A98D7A3A144532E0B32BC623");
        searchQueryParam.setParameter(QueryParameter.QUERY, "collective intelligence");     
        
        BlogQueryResult searchResult = bs.getRelevantBlogs(searchQueryParam);
        System.out.println(searchResult);
        assertTrue("No results", searchResult.getRelevantBlogs().size() > 0);
    }
    
    public void testNaverBlogSearch() throws Exception{
    	BlogSearcher bs = new NaverBlogSearcherImpl();
    	BlogQueryParameter searchQueryParam =
    		new NaverBlogQueryParameterImpl();
    	searchQueryParam.setParameter(QueryParameter.KEY, "f2ff4b6f0a118adac055a85c5b4c5c44");
    	searchQueryParam.setParameter(QueryParameter.LIMIT, "100");
    	searchQueryParam.setParameter(QueryParameter.START_INDEX, "1");
    	searchQueryParam.setParameter(QueryParameter.SORTBY, "sim");
    	searchQueryParam.setParameter(QueryParameter.QUERY, "collective intelligence");
    	
    	BlogQueryResult searchResult = bs.getRelevantBlogs(searchQueryParam);
    	System.out.println(searchResult);
    	assertTrue("No results", searchResult.getRelevantBlogs().size() > 0);
    }
    
    public void testDaumBlogSearch() throws Exception {
    	BlogSearcher bs = new DaumBlogSearcherImpl();
    	BlogQueryParameter searchQueryParam =
    		new DaumBlogQueryParameterImpl();
    	searchQueryParam.setParameter(QueryParameter.KEY, "85351b0cd53b3298af43878c1472fd6792626c5c");
    	searchQueryParam.setParameter(QueryParameter.LIMIT, "10");
    	searchQueryParam.setParameter(QueryParameter.SORTBY, "accu");
    	searchQueryParam.setParameter(QueryParameter.QUERY, "collective intelligence");
    	BlogQueryResult searchResult = bs.getRelevantBlogs(searchQueryParam);
    	System.out.println(searchResult);
    	assertTrue("No results", searchResult.getRelevantBlogs().size() > 0);
    }
    
    public void testBlogdiggerSearch() throws Exception  {
        runRSSProviderTest(new RSSFeedBlogQueryParameterImpl(RSSProviderURL.BLOGDIGGER));
    }
    
    public void testFeedsterSearch() throws Exception  {
        runRSSProviderTest(new RSSFeedBlogQueryParameterImpl(RSSProviderURL.FEEDSTER));
    }
    
    public void testMSNSearch() throws Exception  {
        runRSSProviderTest(new RSSFeedBlogQueryParameterImpl(RSSProviderURL.MSN));      
    }
    
    private void runRSSProviderTest(BlogQueryParameter tagQueryParam)  
        throws Exception  {
        BlogSearcher bs = new RSSFeedBlogSearcherImpl();
   
        tagQueryParam.setParameter(QueryParameter.START_INDEX, "1");
        tagQueryParam.setParameter(QueryParameter.LIMIT, "2");
        tagQueryParam.setParameter(QueryParameter.QUERY, "collective intelligence");       
        BlogQueryResult tagResult = bs.getRelevantBlogs(tagQueryParam);
        System.out.println(tagResult);    
    }
    
    private void runBlogdiggerTest() throws Exception{
        BlogQueryParameter tagQueryParam = new RSSFeedBlogQueryParameterImpl(RSSProviderURL.BLOGDIGGER);
        BlogSearcher bs = new RSSFeedBlogSearcherImpl();
        
        tagQueryParam.setParameter(QueryParameter.START_INDEX, "1");
        tagQueryParam.setParameter(QueryParameter.LIMIT, "2");
        tagQueryParam.setParameter(QueryParameter.QUERY, "collective intelligence");       
        BlogQueryResult tagResult = bs.getRelevantBlogs(tagQueryParam);
        System.out.println(tagResult);  
    }
    
    public static void main(String [] args) throws Exception {
        BlogSearcherTest t = new BlogSearcherTest();
       //t.testTechnoratiTagBlogSearch();
     //  t.testTechnoratiSearchQuery();
        //t.testBloglinesBlogSearch();
       // t.testNaverBlogSearch();
          t.testDaumBlogSearch();
//        t.testBlogdiggerSearch();
//        t.testFeedsterSearch();
//        t.testMSNSearch();
    }
}
