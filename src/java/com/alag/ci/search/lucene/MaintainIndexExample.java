package com.alag.ci.search.lucene;

import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermFreqVector;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.CachingWrapperFilter;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Hit;
import org.apache.lucene.search.HitCollector;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiSearcher;
import org.apache.lucene.search.ParallelMultiSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.RangeFilter;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Searchable;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocCollector;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.similar.MoreLikeThis;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.Lock;

import com.alag.ci.blog.search.BlogQueryResult;
import com.alag.ci.blog.search.RetrievedBlogEntry;

public class MaintainIndexExample extends BlogSearchExample {
    
    public void deleteByIndexId(Directory indexDirectory, int docIndexNum) 
        throws Exception {
        IndexReader indexReader = IndexReader.open(indexDirectory);
        indexReader.deleteDocument(docIndexNum);
        indexReader.close();
    }
    
    public void deleteByTerm(Directory indexDirectory, String externalId)
        throws Exception {
        Term deletionTerm = new Term("externalId", externalId);
        System.out.println();
        IndexReader indexReader = IndexReader.open(indexDirectory);
        indexReader.deleteDocuments(deletionTerm);
        indexReader.close();
    }
    
    public void illustrateBatchModifications(Directory indexDirectory,
            List<Term> deletionTerms,
            List<Document> addDocuments) throws Exception {
        IndexReader indexReader = IndexReader.open(indexDirectory);
        for (Term deletionTerm: deletionTerms) {
            indexReader.deleteDocuments(deletionTerm);
        }
        indexReader.close();
        IndexWriter indexWriter = new IndexWriter(indexDirectory, getAnalyzer(),false);
        for (Document document: addDocuments) {
            indexWriter.addDocument(document);
        }
        indexWriter.optimize();
        indexWriter.close();
    }
    
    public void illustrateLockingCode(Directory indexDirectory,
            List<Term> deletionTerms,
            List<Document> addDocuments) throws Exception {
        if (!IndexReader.isLocked(indexDirectory)) {
            IndexReader indexReader = IndexReader.open(indexDirectory);
            //do work
        } else {
            //wait
        }
    }
    
    public void illustrateQueryCombination(Directory indexDirectory) throws Exception {
        IndexSearcher indexSearcher = new IndexSearcher(indexDirectory);
        PhraseQuery phraseQuery = new PhraseQuery();
        phraseQuery.add(new Term("completeText","collective"));
        phraseQuery.add(new Term("completeText","intelligence"));
        phraseQuery.setSlop(1);
        
        PrefixQuery prefixQuery = new PrefixQuery(new Term("completeText","web"));
        
        BooleanQuery booleanQuery = new BooleanQuery();
        booleanQuery.add(phraseQuery, BooleanClause.Occur.MUST);
        booleanQuery.add(prefixQuery, BooleanClause.Occur.MUST);
        System.out.println(booleanQuery.toString());
        
        Hits hits = indexSearcher.search(booleanQuery);
        Iterator iterator = hits.iterator();
        while (iterator.hasNext()) {
            Hit hit = (Hit) iterator.next();
            Document document = hit.getDocument();
            System.out.println("Title = " + document.get("title"));
            System.out.println(document.get("completeText"));
            
//            Explanation explanation = indexSearcher.explain(weight, hit.getId());
//            System.out.println(explanation.toString());
        }
    }
    
    public void illustrateSorting(Directory indexDirectory) throws Exception {
        IndexSearcher indexSearcher = new IndexSearcher(indexDirectory);
        Sort sort = new Sort("author");
        Query query = new TermQuery(new Term("completeText","intelligence"));
     //   PrefixQuery query = new PrefixQuery(new Term("completeText",""));
        Hits hits = indexSearcher.search(query, sort);
        Iterator iterator = hits.iterator();
        while (iterator.hasNext()) {
            Hit hit = (Hit) iterator.next();
            Document document = hit.getDocument();
            System.out.println("Author = " + document.get("author"));
        }
    }
    
    public Sort getMultipleFieldSort() {
        SortField [] sortFields = {new SortField("author", false), SortField.FIELD_SCORE,
                SortField.FIELD_DOC};
        return new Sort(sortFields);
    }
    
    public QueryParser getMultiFieldQuery() throws Exception {
        String [] fields = {"name", "title", "excerpt"};
        return new MultiFieldQueryParser(fields, getAnalyzer());
    }
    
    public Query getMultiFieldAndQuery(String query) throws Exception {
        String [] fields = {"name", "title", "excerpt"};
        BooleanClause.Occur[] flags = {BooleanClause.Occur.SHOULD,
                BooleanClause.Occur.MUST,
                BooleanClause.Occur.MUST_NOT};
        return MultiFieldQueryParser.parse(query, fields, flags, getAnalyzer());
    }
    
    public void illustrateFilterSearch(IndexSearcher indexSearcher,
            Query query, Sort sort) throws Exception {
        Filter rangeFilter = new RangeFilter("modifiedDate", "20080101", 
                "20080131", true, true);
        CachingWrapperFilter cachedFilter = new CachingWrapperFilter(rangeFilter);
        Hits hits = indexSearcher.search(query, cachedFilter, sort);
    }
    
    public void illustrateMultipleIndexSearchers(Directory index1, 
            Directory index2, Query query, Filter filter) throws Exception {
        IndexSearcher indexSearcher1 = new IndexSearcher(index1);
        IndexSearcher indexSearcher2 = new IndexSearcher(index2);
        Searchable [] searchables = {indexSearcher1, indexSearcher2};
        
        Searcher searcher = new MultiSearcher(searchables);
        Searcher parallelSearcher = new ParallelMultiSearcher(searchables);
        
        Hits hits = searcher.search(query, filter);
        indexSearcher1.close();
        indexSearcher2.close();
    }
//    public void illustrateFlushByRAM(IndexWriter indexWriter,
//            List<Document> documents) throws Exception {
//        indexWriter.setMaxBufferedDocs(MAX_BUFFER_VERY_LARGE_NUMBER);       
//        for (Document document: documents) {
//            indexWriter.addDocument(document);
//            long currentSize = indexWriter.ramSizeInBytes();
//            if (currentSize > LUCENE_MAX_RAM) {
//                indexWriter.flush();
//            }
//        }
//    }
    
    public void illustrateTopDocs(Directory indexDirectory, Query query,
            int maxNumHits) throws Exception {
        IndexSearcher indexSearcher = new IndexSearcher(indexDirectory);
        TopDocCollector hitCollector = new TopDocCollector(maxNumHits);
        indexSearcher.search(query, hitCollector);
        TopDocs topDocs = hitCollector.topDocs();
        System.out.println("Total number results=" + topDocs.totalHits);
        for (ScoreDoc scoreDoc: topDocs.scoreDocs) {
            Document document = indexSearcher.doc(scoreDoc.doc);
            System.out.println(document.get("completeText"));
        }
        indexSearcher.close();
    }
    
    public void illustrateTermFreqVector(Directory indexDirectory) throws Exception {
        IndexReader indexReader = IndexReader.open(indexDirectory);
        for (int i = 0; i < indexReader.numDocs(); i ++) {
            System.out.println("Blog " + i);
            TermFreqVector termFreqVector = 
                indexReader.getTermFreqVector(i, "completeText");
            String [] terms = termFreqVector.getTerms();
            int [] freq = termFreqVector.getTermFrequencies();
            for (int j =0 ; j < terms.length; j ++) {
                System.out.println(terms[j] + " " + freq[j]);
            }
        }
    }
    
    public void illustrateMoreLikeThisByQuery(Directory indexDirectory) throws Exception {
        IndexSearcher indexSearcher = new IndexSearcher(indexDirectory);     
        for (int i = 0; i < indexSearcher.maxDoc(); i ++) {
            Document document = indexSearcher.doc(i);
            System.out.println(document.get("title") + " " + document.get("url"));
            if (document != null) {
                List<RetrievedBlogEntry> relatedBlogs = 
                    getRelatedBlogsUsingLuceneMoreLikeThis( indexSearcher, i) ;
//               List<RetrievedBlogEntry> relatedBlogs = getRelatedBlogEntries( indexSearcher, i) ;
                for (RetrievedBlogEntry relatedBlog : relatedBlogs ) {
                    System.out.println("\t" + relatedBlog.getTitle() + " " + relatedBlog.getUrl());
                }
            }
        }
    }
    
    private List<RetrievedBlogEntry> getRelatedBlogEntries(IndexSearcher indexSearcher,
             int docNum) throws Exception {
        IndexReader indexReader = indexSearcher.getIndexReader();
        
        Query booleanTitleQuery = composeTermVectorBooleanQuery(indexReader,
                 docNum,"title", 3.0f);
        Query booleanCompleteTextQuery =composeTermVectorBooleanQuery(indexReader,
                docNum,"completeText", 1.0f);
            
        BooleanQuery likeThisQuery = new BooleanQuery();
        likeThisQuery.add(booleanTitleQuery, BooleanClause.Occur.SHOULD);
        likeThisQuery.add(booleanCompleteTextQuery, BooleanClause.Occur.SHOULD);
        
        return getRelatedBlogEntries(indexSearcher, likeThisQuery);
    }
    
    private List<RetrievedBlogEntry> getRelatedBlogEntries(IndexSearcher indexSearcher,
            Query query) throws Exception {
        RetrievedBlogHitCollector blogHitCollector = new RetrievedBlogHitCollector(indexSearcher);
        indexSearcher.search(query, blogHitCollector);
        return blogHitCollector.getBlogEntries();
    }
    
    private Query composeTermVectorBooleanQuery(IndexReader indexReader,
            int docNum,String fieldName, float boost ) throws Exception {
        TermFreqVector termFreqVector = 
            indexReader.getTermFreqVector(docNum, fieldName);
        
        BooleanQuery booleanQuery = new BooleanQuery();
        String [] terms = termFreqVector.getTerms();
        for (String term: terms) {
            Query termQuery = new TermQuery(new Term(fieldName,term));
            booleanQuery.add(termQuery, BooleanClause.Occur.SHOULD);
        }
        booleanQuery.setBoost(boost);
        return booleanQuery;
    }
    
    public List<RetrievedBlogEntry>  getRelatedBlogsUsingLuceneMoreLikeThis(IndexSearcher indexSearcher, 
            int docNum) throws Exception {
        IndexReader indexReader = indexSearcher.getIndexReader();
        MoreLikeThis moreLikeThis = new MoreLikeThis(indexReader);
        moreLikeThis.setAnalyzer(getAnalyzer());
        String [] fieldNames = {"title", "completeText"};
        moreLikeThis.setFieldNames(fieldNames);
        moreLikeThis.setMinDocFreq(0);
        Query query = moreLikeThis.like(docNum);
        System.out.println(query.toString());
        return getRelatedBlogEntries( indexSearcher, query);
    }

    public static void main(String [] args) throws Exception {
        MaintainIndexExample bs = new MaintainIndexExample();
        String tag = "collective intelligence";
        String luceneIndexPath = "blogSearchIndex";
        
        BlogQueryResult blogQueryResult = 
      //      bs.getBlogsFromTechnorati(tag);
        	bs.getBlogFromDaum(tag);
        Directory indexDirectory = bs.createSearchIndex(luceneIndexPath, blogQueryResult);
      //  bs.illustrateTermFreqVector(indexDirectory);
     //   bs.illustrateQueryCombination(indexDirectory);
        // bs.searchForBlogs(indexDirectory, tag);
      //  bs.illustrateSorting(indexDirectory);
//        Query query = new TermQuery(new Term("completeText","intelligence"));
//        bs.illustrateTopDocs(indexDirectory, query, 5);
        IndexReader indexReader = IndexReader.open(indexDirectory);
        Query query = bs.composeTermVectorBooleanQuery( indexReader,
                1,"completeText", 1 );
      //  System.out.println("\n\nQuery = " + query);
        bs.illustrateMoreLikeThisByQuery(indexDirectory);
        
    }

	
}
