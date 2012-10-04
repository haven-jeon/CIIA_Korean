package com.alag.ci.search.lucene;

import java.io.IOException;
import java.util.*;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.*;

import com.alag.ci.blog.search.RetrievedBlogEntry;
import com.alag.ci.blog.search.impl.RetrievedBlogEntryImpl;

public class RetrievedBlogHitCollector extends HitCollector{
    private List<RetrievedBlogEntry> blogs = null;
    private Searcher searcher = null;
    
    public RetrievedBlogHitCollector(Searcher searcher) {
        this.searcher = searcher;
        this.blogs = new ArrayList<RetrievedBlogEntry>();
    }
    
    public  void collect(int docNum, float score) {
        try {
            Document document = this.searcher.doc(docNum);
            RetrievedBlogEntryImpl blogEntry = new RetrievedBlogEntryImpl();
            blogEntry.setAuthor(document.get("author"));
            blogEntry.setTitle(document.get("title"));
            blogEntry.setUrl(document.get("url"));
            this.blogs.add(blogEntry);
        } catch (IOException e) {
            //ignored
        }
    }
    
    public List<RetrievedBlogEntry> getBlogEntries() {
        return this.blogs;
    }
    
}
