package com.alag.ci.recoengine;

import com.alag.ci.cluster.TextDataItem;

public class RelevanceTextDataItem  implements Comparable<RelevanceTextDataItem>{
    private double relevance = 0;
    private TextDataItem dataItem = null;
    
    public RelevanceTextDataItem(TextDataItem dataItem, double relevance) {
        this.relevance = relevance;
        this.dataItem = dataItem;
    }
    
    public int compareTo(RelevanceTextDataItem other) {
        if( this.relevance > other.getRelevance()) {
            return -1;
        }
        if( this.relevance < other.getRelevance()) {
            return 1;
        }
        return 0;
    }

    public TextDataItem getDataItem() {
        return dataItem;
    }

    public double getRelevance() {
        return relevance;
    }
}