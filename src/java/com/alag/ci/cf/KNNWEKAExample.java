package com.alag.ci.cf;

import weka.classifiers.lazy.IBk;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class KNNWEKAExample {
    
    public static final void main(String [] args) throws Exception {
        KNNWEKAExample eg = new KNNWEKAExample();
        FastVector attributes = eg.createAttributes();
        Instances instances = eg.createLearningDataSet(attributes);
        eg.illustrateClassification(instances);
    }
    
    private FastVector createAttributes() {
        FastVector allAttributes = new FastVector(3);
        allAttributes.addElement(new Attribute("item1"));
        allAttributes.addElement(new Attribute("item2"));
        allAttributes.addElement(new Attribute("item3"));
        return allAttributes;
    }
    
    private Instances createLearningDataSet(FastVector allAttributes) {
        Instances trainingDataSet = 
            new Instances("wekaCF", allAttributes, 3); 
        trainingDataSet.setClassIndex(2);
        addInstance(trainingDataSet, 3,4,2);
        addInstance(trainingDataSet, 2,2,4);
        addInstance(trainingDataSet, 1,3,5);
        System.out.println(trainingDataSet);
        return trainingDataSet;
    }

    private void addInstance(Instances trainingDataSet, 
            double item1, double item2, double item3)  {   
        // Create empty instance with three attribute values
        Instance instance = new Instance(3);
        instance.setDataset(trainingDataSet);
        instance.setValue(0, item1);
        instance.setValue(1, item2);
        instance.setValue(2, item3);
        trainingDataSet.add(instance);
    }
    
    public void illustrateClassification(Instances instances) throws Exception {     
        IBk ibk = new IBk(1);
        ibk.buildClassifier(instances);
        System.out.println("\nPrediction:");
        for (int i = 0; i < instances.numInstances(); i++) {
            Instance instance = instances.instance(i);
            double  result = ibk.classifyInstance(instance);
            System.out.println("Expected=" + instance.value(2) + " Predicted=" + result);      
        }
    }
}
