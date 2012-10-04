package com.alag.ci.blog.predict.weka.impl;

import weka.classifiers.Classifier;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.RBFNetwork;
import weka.core.Instance;
import weka.core.Instances;

import com.alag.ci.blog.classify.weka.impl.WEKABlogClassifier;
import com.alag.ci.blog.dataset.impl.WEKAPredictiveBlogDataSetCreatorImpl;

public class WEKABlogPredictor extends WEKABlogClassifier {
 
    protected Instances createLearningDataset() throws Exception {
        WEKAPredictiveBlogDataSetCreatorImpl dataSetCreator = 
            new WEKAPredictiveBlogDataSetCreatorImpl();
        return dataSetCreator.createLearningDataSet("continuousBlogData",true);
    }
    
    protected Classifier getClassifier(Algorithm algorithm) throws Exception {
        //last attribute is used for classification
        Classifier classifier = null;
        if (WEKABlogClassifier.Algorithm.LINEAR_REGRESSION.equals(algorithm)) {
            classifier = new LinearRegression();
        } else if (WEKABlogClassifier.Algorithm.MLP.equals(algorithm)) {
            classifier = new MultilayerPerceptron() ;
        } else if (WEKABlogClassifier.Algorithm.RBF.equals(algorithm)) {
            classifier = new RBFNetwork();
        }
        return classifier;
    }
    
    protected void printInstancePrediction(Instance instance, 
            Classifier classifier) throws Exception {
        double [] prediction = classifier.distributionForInstance(instance);
        System.out.print("\nPrediction = " );
        for (double value: prediction) {
            System.out.print(value + ", ");
        }
    }
    
    public double gain(double p, double n) {
        double sum = p+n;
        double  gain = p*log2(p/sum)/sum  + n*log2(n/sum)/sum;;
        return -gain;
    }
    
    private double log2(double x) {
        if (x <= 0.) {
            return 0.;
        }
        return Math.log10(x)/Math.log10(2.);
    }
    
    public static final void main(String [] args) throws Exception {
        WEKABlogPredictor c = new WEKABlogPredictor();
        c.classify(WEKABlogClassifier.Algorithm.MLP);
//        double c12 = c.gain(1, 2);
//        double c11 = c.gain(1, 1);
//        double net = 0.75*c12 ;
//        System.out.println("net=" + net);
//        System.out.println("p="+ 1 + " n=" + 2 + " " + c.gain(1, 2));
//        System.out.println("p="+ 2 + " n=" + 2 + " " + c.gain(2, 2));
//        System.out.println("p="+ 2 + " n=" + 3 + " " + c.gain(2, 3));
        
    }

}
