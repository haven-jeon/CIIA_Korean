package com.alag.ci.blog.dataset.impl;

import java.io.IOException;
import java.util.*;

import weka.core.*;

import com.alag.ci.blog.search.BlogQueryResult;
import com.alag.ci.cluster.TextDataItem;
import com.alag.ci.textanalysis.*;
import com.alag.ci.textanalysis.Tag;

public class WEKAPredictiveBlogDataSetCreatorImpl extends
        BlogDataSetCreatorImpl {
    private List<TextDataItem> blogEntries = null;
    
    public WEKAPredictiveBlogDataSetCreatorImpl() throws Exception {     
    }
    
    private List<TextDataItem> createLearningDataSet(boolean isContinuous) throws Exception {
        String [] positiveTags = { "collective intelligence", "data mining", "web 2.0"};
        String [] negativeTags = { "child intelligence", "AJAX"};
        return createLearningDataSet(positiveTags, negativeTags, isContinuous);
    }
    
    private List<TextDataItem> createLearningDataSet(String [] positiveTags, String [] negativeTags, 
            boolean isContinuous) throws Exception {
        List<TextDataItem> data = new ArrayList<TextDataItem>();
        for (String tag: positiveTags) {
            data.addAll(getBlogData(tag, true));
        }
        for (String tag: negativeTags) {
            data.addAll(getBlogData(tag, false));
        }
        return data;
    }
  
    private List<TextDataItem> getBlogData(String tag, boolean isRelevant)
        throws Exception {
        //BlogQueryResult bqrCI = getBlogsFromTechnorati(tag);
    	BlogQueryResult bqrCI = getBlogFromDaum(tag);
        return  getBlogTagMagnitudeVectors(bqrCI,isRelevant);
    }
    
    protected List<TextDataItem> getBlogTagMagnitudeVectors(
            BlogQueryResult blogQueryResult, boolean isRelevant) throws IOException {
        List<TextDataItem> tdiList = super.getBlogTagMagnitudeVectors(blogQueryResult);
        for (TextDataItem dataItem: tdiList) {
            dataItem.setCiRelated(isRelevant);
        }
        return tdiList;
    }
 
    public Instances createLearningDataSet(String datasetName, boolean isContinuous) throws Exception {
        this.blogEntries = createLearningDataSet(isContinuous);
        FastVector allAttributes = createAttributes(isContinuous);
        Instances trainingDataSet = new Instances(datasetName,
                allAttributes, blogEntries.size());
        int numAttributes = allAttributes.size();
//        System.out.println("Number attributes =" + numAttributes);
        Collection<Tag> allTags = this.getAllTags();
        for (TextDataItem dataItem : blogEntries) {
            Instance instance = createNewInstance(numAttributes,trainingDataSet,
                     allTags, dataItem, isContinuous );
            trainingDataSet.add(instance);
        }
        System.out.println(trainingDataSet);
        return trainingDataSet;
    }
    
    protected FastVector createAttributes(boolean isContinuous) {
        Collection<Tag> allTags = this.getAllTags();
        FastVector allAttributes = new FastVector(allTags.size());
        for (Tag tag : allTags) {
            Attribute tagAttribute = createAttribute(tag.getDisplayText(),isContinuous);
            allAttributes.addElement(tagAttribute);
        }
       Attribute classificationAttribute = createAttribute("ClassificationAttribute",isContinuous);
       allAttributes.addElement(classificationAttribute);
       return allAttributes;
    }
    private Attribute createAttribute(String attributeName, boolean isContinuous) {
        if (isContinuous) {
            return createContinuousAttribute(attributeName);
        }
        return createBinaryNominalAttribute(attributeName);
    }
    private Attribute createBinaryNominalAttribute(String attributeName) {
        FastVector attNominalValues = new FastVector(2);
        attNominalValues.addElement("true");
        attNominalValues.addElement("false");
       return new Attribute(attributeName,attNominalValues);
    }
    
    private Attribute createContinuousAttribute(String attributeName) {
       return new Attribute(attributeName);
    }
    
    protected Instance createNewInstance(int numAttributes,
            Instances trainingDataSet, Collection<Tag> allTags,
            TextDataItem dataItem,boolean isContinuous) {
        Instance instance = new Instance(numAttributes);
        instance.setDataset(trainingDataSet);
        int index = 0;
        TagMagnitudeVector tmv = dataItem.getTagMagnitudeVector();
        Map<Tag, TagMagnitude> tmvMap = tmv.getTagMagnitudeMap();
        for (Tag tag : allTags) {
            TagMagnitude tm = tmvMap.get(tag);
            if (tm != null) {
                setInstanceValue(instance,index++,tm.getMagnitude(), isContinuous);
            } else {
                setInstanceValue(instance,index++,0., isContinuous);
            }
        }
        BlogAnalysisDataItem blog = (BlogAnalysisDataItem) dataItem;
        if (blog.isCiRelated()) {
            setInstanceValue(instance,index, 1., isContinuous);
        } else {
            setInstanceValue(instance,index, 0., isContinuous);
        }
        return instance;
    }
    
    private void setInstanceValue(Instance instance, int index, double magnitude, boolean isContinuous) {
        if (isContinuous) {
            instance.setValue(index, magnitude);
        } else {
            if (magnitude > 0.) {
                instance.setValue(index, "true");
            } else {
                instance.setValue(index, "false");
            }
        }
    }
    
    public static void main(String [] args) throws Exception {
        WEKAPredictiveBlogDataSetCreatorImpl dataCreator = new WEKAPredictiveBlogDataSetCreatorImpl();
        Instances discreteDataSet = dataCreator.createLearningDataSet("nominalBlogData", false);
        System.out.println(discreteDataSet.toSummaryString());
//        Instances continuousDataSet = dataCreator.createLearningDataSet("continuousBlogData",true);
//        System.out.println(continuousDataSet);
    }

}
