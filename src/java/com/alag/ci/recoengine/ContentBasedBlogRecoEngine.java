package com.alag.ci.recoengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alag.ci.blog.dataset.impl.BlogDataSetCreatorImpl;
import com.alag.ci.blog.search.RetrievedBlogEntry;
import com.alag.ci.cluster.DataSetCreator;
import com.alag.ci.cluster.TextDataItem;
import com.alag.ci.textanalysis.TagMagnitude;
import com.alag.ci.textanalysis.TagMagnitudeVector;
import com.alag.ci.textanalysis.termvector.impl.TagMagnitudeVectorImpl;

public class ContentBasedBlogRecoEngine {
    
    public static void main(String [] args) throws Exception{
        ContentBasedBlogRecoEngine recoEngine = new ContentBasedBlogRecoEngine();
        List<TextDataItem> dataItems = recoEngine.createLearningData();
        recoEngine.illustrateContentRecoEngine(dataItems);
        List<TagMagnitudeVector> tmvList = new ArrayList<TagMagnitudeVector>();
        for (TextDataItem dataItem: dataItems) {
            tmvList.add(dataItem.getTagMagnitudeVector());
        }
        recoEngine.illustrateMergingOfDocuments(tmvList);
        
    }

    public List<TextDataItem> createLearningData() throws Exception {
        DataSetCreator creator =  new BlogDataSetCreatorImpl();
        return creator.createLearningData();
    }
    
    public void illustrateContentRecoEngine(List<TextDataItem> dataItems) {
        for (TextDataItem dataItem: dataItems) {
            RetrievedBlogEntry blogEntry = (RetrievedBlogEntry)dataItem.getData();
            System.out.println(blogEntry.getTitle() + " " + blogEntry.getUrl());
            List<RelevanceTextDataItem> relevantItems = getRelevantDataItems(
                    dataItem,dataItems ) ;
            for (RelevanceTextDataItem relevantItem: relevantItems) {
                blogEntry = (RetrievedBlogEntry)(relevantItem.getDataItem().getData());
                System.out.println("\t" + blogEntry.getTitle() + " " + blogEntry.getUrl()
                		+ 
                		" " + relevantItem.getRelevance());
            }
        }
    }
    
    private List<RelevanceTextDataItem> getRelevantDataItems(
            TextDataItem parentDataItem,List<TextDataItem> candidateDataItems ) {
        TagMagnitudeVector tmv = parentDataItem.getTagMagnitudeVector();
        List<RelevanceTextDataItem> relevantItems = new ArrayList<RelevanceTextDataItem>();
        for (TextDataItem candidateDataItem: candidateDataItems) {
            if (!parentDataItem.equals(candidateDataItem)) {
                double relevance = tmv.dotProduct(candidateDataItem.getTagMagnitudeVector());
                if (relevance > 0.) {
                    relevantItems.add(new RelevanceTextDataItem(candidateDataItem, relevance));
                }
            }
        }
        Collections.sort(relevantItems);
        return relevantItems;
    }
    
    public void illustrateMergingOfDocuments(List<TagMagnitudeVector> tagMagnitudeVectors) {
        List<TagMagnitude> tagMagnitudes = Collections.emptyList();
        TagMagnitudeVector emptyTMV = new TagMagnitudeVectorImpl(tagMagnitudes);
        TagMagnitudeVector mergedTMV = emptyTMV.add(tagMagnitudeVectors);
        System.out.println(mergedTMV);      
    }
}
