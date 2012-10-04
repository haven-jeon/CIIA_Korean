package com.alag.ci.blog.dataset.impl;

import java.io.IOException;
import java.util.*;

import com.alag.ci.blog.search.*;
import com.alag.ci.blog.search.BlogQueryParameter.QueryParameter;
import com.alag.ci.blog.search.impl.daum.DaumBlogQueryParameterImpl;
import com.alag.ci.blog.search.impl.daum.DaumBlogSearcherImpl;
import com.alag.ci.blog.search.impl.technorati.*;
import com.alag.ci.cluster.*;
import com.alag.ci.textanalysis.*;
import com.alag.ci.textanalysis.lucene.impl.*;

public class BlogDataSetCreatorImpl implements DataSetCreator {
   private TagCache tagCache = null ;
   
   public BlogDataSetCreatorImpl() {
       this.tagCache = new TagCacheImpl();
   }
   
    public List<TextDataItem> createLearningData()
        throws Exception {
    	//테크노라티 API
        //BlogQueryResult bqr = getBlogsFromTechnorati("collective intelligence");
    	BlogQueryResult bqr = getBlogFromDaum("collective intelligence");
        return getBlogTagMagnitudeVectors(bqr);
    }
    
  //Daum 검색 결과 다운 로드 메서드 
    public BlogQueryResult getBlogFromDaum(String tag) 
    	throws BlogSearcherException {
    	BlogSearcher bs = new DaumBlogSearcherImpl();
    	//BlogQueryParameter tagQueryParam = 
    	BlogQueryParameter tagQueryParam =
    		new DaumBlogQueryParameterImpl();
    	tagQueryParam.setParameter(QueryParameter.KEY, "85351b0cd53b3298af43878c1472fd6792626c5c");
    	tagQueryParam.setParameter(QueryParameter.LIMIT, "10");
    	tagQueryParam.setParameter(QueryParameter.SORTBY, "accu");
    	tagQueryParam.setParameter(QueryParameter.QUERY, tag);
    	BlogQueryResult searchResult = bs.getRelevantBlogs(tagQueryParam);
    	return bs.getRelevantBlogs(tagQueryParam);
    }
    
    public TagCache getTagCache() {
        return tagCache;
    }

    public Collection<Tag> getAllTags() {
        return this.tagCache.getAllTags();
    }
    //테크노라티 API 
    public BlogQueryResult getBlogsFromTechnorati(String tag) 
        throws BlogSearcherException{
        BlogSearcher bs = new TechnoratiBlogSearcherImpl();
    
        BlogQueryParameter tagQueryParam = 
            new TechnoratiTagBlogQueryParameterImpl();
        tagQueryParam.setParameter(QueryParameter.KEY,
                "aa2d0294b0b14fff42f68b81d399d0e1");
        tagQueryParam.setParameter(QueryParameter.LIMIT, "10");
        tagQueryParam.setParameter(QueryParameter.TAG,tag);
        tagQueryParam.setParameter(QueryParameter.LANGUAGE, "en");  
        
        return bs.getRelevantBlogs(tagQueryParam);
    }
    
    
    protected List<TextDataItem> getBlogTagMagnitudeVectors(
            BlogQueryResult blogQueryResult) throws IOException {
        List<RetrievedBlogEntry> blogEntries = blogQueryResult.getRelevantBlogs();
        List<TextDataItem> result = new ArrayList<TextDataItem>();
        InverseDocFreqEstimator freqEstimator = 
            new InverseDocFreqEstimatorImpl(blogEntries.size());
        TextAnalyzer textAnalyzer = new LuceneTextAnalyzer(
                this.tagCache, freqEstimator);
            
        for (RetrievedBlogEntry blogEntry: blogEntries) {
            String text = composeTextForAnalysis(blogEntry);
            TagMagnitudeVector tmv = textAnalyzer.createTagMagnitudeVector(text);
            for (TagMagnitude tm: tmv.getTagMagnitudes()) {
                freqEstimator.addCount(tm.getTag());
            }
        }
        
        for (RetrievedBlogEntry blogEntry: blogEntries) {
            String text = composeTextForAnalysis(blogEntry);
            TagMagnitudeVector tmv = textAnalyzer.createTagMagnitudeVector(text);
            result.add(getBlogAnalysisDataItem(blogEntry,tmv) );
        }
        return result;
    }
    
    protected BlogAnalysisDataItem getBlogAnalysisDataItem(RetrievedBlogEntry blogEntry,
            TagMagnitudeVector tmv) {
        return new BlogAnalysisDataItem(blogEntry,tmv);
    }
    
    public String composeTextForAnalysis(RetrievedBlogEntry blogEntry) {
        StringBuilder sb = new StringBuilder();
        if (blogEntry.getTitle() != null) {
            sb.append(blogEntry.getTitle());
        }
        if (blogEntry.getName() != null) {
            sb.append(" " + blogEntry.getName());
        }
        if (blogEntry.getAuthor() != null) {
            sb.append(" " + blogEntry.getAuthor());
        }
        if (blogEntry.getExcerpt() != null) {
            sb.append(" " + blogEntry.getExcerpt());
        }
        return sb.toString();
    }
    
    public static void main(String [] args) throws Exception {
        BlogDataSetCreatorImpl bc = new BlogDataSetCreatorImpl();
        List<TextDataItem> beList = bc.createLearningData();
        for (TextDataItem tm: beList) {
            System.out.println(tm.getTagMagnitudeVector());
        }
    }
    
}
