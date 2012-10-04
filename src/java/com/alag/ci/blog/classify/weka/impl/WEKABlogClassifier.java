package com.alag.ci.blog.classify.weka.impl;

import java.util.Enumeration;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayesSimple;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;

import com.alag.ci.blog.dataset.impl.WEKAPredictiveBlogDataSetCreatorImpl;

public class WEKABlogClassifier {
    public enum Algorithm  {DECISION_TREE, NAIVE_BAYES, BAYES_NET,
        LINEAR_REGRESSION, MLP, RBF};
    
    public void classify(Algorithm algorithm) throws Exception {
        Instances instances = createLearningDataset();
        Classifier classifier = getClassifier(instances,algorithm);
        evaluateModel(instances, classifier);
    }
    
    protected Instances createLearningDataset() throws Exception {
        WEKAPredictiveBlogDataSetCreatorImpl dataSetCreator = 
            new WEKAPredictiveBlogDataSetCreatorImpl();
        return dataSetCreator.createLearningDataSet("nominalBlogData",false);
    }
    
    protected void evaluateModel(Instances instances, Classifier classifier) 
        throws Exception {
        Evaluation modelEval = new Evaluation(instances);
        modelEval.evaluateModel(classifier, instances);
        System.out.println(modelEval.toSummaryString("\nResults\n", true));
       // System.out.println(((J48)classifier).graph());
        for (Enumeration e = instances.enumerateInstances() ; e.hasMoreElements() ;) {
            printInstancePrediction((Instance)e.nextElement(),classifier);
        }
    }
    
    protected void printInstancePrediction(Instance instance, 
            Classifier classifier) throws Exception {
        double classification = classifier.classifyInstance(instance);
        System.out.println("Classification = " + classification);
    }
 
    protected Classifier getClassifier(Instances instances,
         Algorithm algorithm) throws Exception {
        Classifier classifier = getClassifier(algorithm);
        //last attribute is used for classification
        instances.setClassIndex(instances.numAttributes() - 1);
        classifier.buildClassifier(instances);
        return classifier;
    }
    
    protected Classifier getClassifier(Algorithm algorithm) throws Exception {
           Classifier classifier = null;
           if (Algorithm.DECISION_TREE.equals(algorithm)) {
               classifier = new J48();
           } else if (Algorithm.NAIVE_BAYES.equals(algorithm)) {
               classifier = new NaiveBayesSimple() ;
           } else if (Algorithm.BAYES_NET.equals(algorithm)) {
               classifier = new BayesNet() ;
           } 
           System.out.println(classifier.getCapabilities());
           return classifier;
    }
    
    public static void main(String [] args) throws Exception {
        WEKABlogClassifier c = new WEKABlogClassifier();
        c.classify(Algorithm.DECISION_TREE);
    }

}
