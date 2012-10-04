package com.alag.ci.search.lucene;

import java.io.IOException;
import java.util.*;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;

import com.alag.ci.blog.dataset.impl.BlogDataSetCreatorImpl;
import com.alag.ci.blog.search.*;
import com.alag.ci.textanalysis.lucene.impl.*;

public class BlogSearchExample {

    public BlogQueryResult getBlogsFromTechnorati(String tag) 
        throws BlogSearcherException {
        return (new BlogDataSetCreatorImpl()).getBlogsFromTechnorati(tag);
    }
    
    public BlogQueryResult getBlogFromDaum(String tag)
    	throws BlogSearcherException {
    	return (new BlogDataSetCreatorImpl()).getBlogFromDaum(tag);
    }
    
    public Directory createSearchIndex(String path, 
            BlogQueryResult blogQueryResult) throws  Exception{
        IndexWriter indexWriter = new IndexWriter(path, getAnalyzer(), true);
      //  indexWriter.setUseCompoundFile(false);       
        Directory indexDirectory = indexWriter.getDirectory();
        indexBlogEntries(indexWriter, blogQueryResult.getRelevantBlogs());
        System.out.println("Number of docs indexed = " + indexWriter.docCount());
        indexWriter.optimize();
        indexWriter.close();
        return indexDirectory;
    }
       
    private void indexBlogEntries(IndexWriter indexWriter, 
            List<RetrievedBlogEntry> blogEntries) throws Exception {       
        for (RetrievedBlogEntry blogEntry: blogEntries) {
            indexWriter.addDocument(getDocument(blogEntry));
        }
    }
    
    private Document getDocument(RetrievedBlogEntry blogEntry) {
        Document document = new Document();
        BlogDataSetCreatorImpl dataSetCreator = new BlogDataSetCreatorImpl();
        String completeText = dataSetCreator.composeTextForAnalysis(
                blogEntry);
        addField(document,"completeText",completeText, Field.Store.YES,
                Field.Index.TOKENIZED , Field.TermVector.YES);
        addField(document,"name",blogEntry.getName(), Field.Store.YES,
                Field.Index.TOKENIZED , Field.TermVector.YES);
        addField(document,"title",blogEntry.getTitle(), Field.Store.YES,
                Field.Index.TOKENIZED , Field.TermVector.YES);
        addField(document,"excerpt",blogEntry.getExcerpt(), Field.Store.YES,
                Field.Index.TOKENIZED , Field.TermVector.YES);
        addField(document,"url",blogEntry.getUrl(), Field.Store.YES,
                Field.Index.UN_TOKENIZED , Field.TermVector.NO);
        addField(document,"author",blogEntry.getAuthor(), Field.Store.YES,
                Field.Index.UN_TOKENIZED , Field.TermVector.YES);
        return document;
    }
    
    private void addField(Document document, String fieldName,String value,  
            Field.Store filedStore, Field.Index fieldIndex,Field.TermVector fieldTermVector) {
        Field field = new Field(fieldName, getNotNullValue(value), 
                filedStore, fieldIndex, fieldTermVector);
        document.add(field);
    }
    
    private String getNotNullValue(String s) {
        if (s != null) {
            return s;
        }
        return "";
    }
    
    protected Analyzer getAnalyzer() throws IOException {
        return new SynonymPhraseStopWordAnalyzer(
                new SynonymsCacheImpl(),new PhrasesCacheImpl());
    }
    
    public void searchForBlogs(Directory directory, String queryString) throws Exception {
        IndexSearcher indexSearcher = new IndexSearcher(directory);
        QueryParser queryParser = new QueryParser("completeText",getAnalyzer());
        Query query = queryParser.parse(queryString);
        Hits hits = indexSearcher.search(query);
        System.out.println("Number of results = " + hits.length() + 
                " for " + queryString);
        Weight weight = query.weight(indexSearcher);
        Iterator iterator = hits.iterator();
        while (iterator.hasNext()) {
            Hit hit = (Hit) iterator.next();
            Document document = hit.getDocument();
          //  System.out.println("Title = " + document.get("title"));
            System.out.println(document.get("completeText"));
            
            Explanation explanation = indexSearcher.explain(weight, hit.getId());
            System.out.println(explanation.toString());
        }
        indexSearcher.close();
    }
    
    
    
    //1. adding and deleting documents to an index
    //2. synonyms e.g
    //3. boosting
     //4. to sort by a different field, add as field untokenized,
     //must be convertible to floats, ints, or strings
     //2.7 performance tuning of lucene
    //To boost a document simply use
    // document.setBoost(boost);
     //field.setBoost(boost);
    
    public static void main(String [] args) throws Exception {
        BlogSearchExample bs = new BlogSearchExample();
        String tag = "collective intelligence";
        String luceneIndexPath = "blogSearchIndex-compound";
        
        BlogQueryResult blogQueryResult = 
            //bs.getBlogsFromTechnorati(tag);      
        	bs.getBlogFromDaum(tag);
        Directory indexDirectory = bs.createSearchIndex(luceneIndexPath, blogQueryResult);
        bs.searchForBlogs(indexDirectory, tag);
        System.out.println("Locked =" + IndexReader.isLocked(indexDirectory));
        IndexReader indexReader = IndexReader.open(indexDirectory);
        System.out.println("Locked =" + IndexReader.isLocked(indexDirectory));
        
    }
}
