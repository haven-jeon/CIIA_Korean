package com.alag.ci.jdm.classification;
//
//import javax.datamining.*;
//import javax.datamining.algorithm.svm.KernelFunction;
//import javax.datamining.algorithm.svm.classification.*;
//import javax.datamining.resource.Connection;
//import javax.datamining.supervised.SupervisedAlgorithmSettings;
//import javax.datamining.supervised.classification.*;
//import javax.datamining.task.*;

public class JDMClassificationExample {
//    
//    public void classify(Connection connection) throws JDMException {
//        createClassificationSettings(connection);
//        createClassificationTask(connection);
//        executeClassificationTask(connection);
//        retrieveClassificationModel(connection);
//        testClassificationModel(connection);
//    }
//    
//    private void createClassificationSettings(Connection connection) throws JDMException {
//        ClassificationSettingsFactory classifSettingsFactory = (ClassificationSettingsFactory)
//        connection.getFactory(
//                "javax.datamining.supervised.classification.ClassificationSettingsFactory");
//        ClassificationSettings classificationSettings = classifSettingsFactory.create();
//        classificationSettings.setCostMatrixName("costMatrixName");
//        classificationSettings.setTargetAttributeName("targetAttributeName");
//        
//        SupervisedAlgorithmSettings algorithmSettings = createSVMClassificationSettings(connection);
//        classificationSettings.setAlgorithmSettings(algorithmSettings);
//        connection.saveObject("classificationSettings", classificationSettings, false);
//    }
//    
//    private SupervisedAlgorithmSettings createSVMClassificationSettings(Connection connection) throws JDMException {
//        SVMClassificationSettingsFactory svmClassificationSettingsFactory = (SVMClassificationSettingsFactory)
//        connection.getFactory(
//                "javax.datamining.algorithm.svm.classification.SVMClassificationSettingsFactory");
//        SVMClassificationSettings svmSettings = svmClassificationSettingsFactory.create();
//        svmSettings.setKernelFunction(KernelFunction.kGaussian);
//        return svmSettings;
//    }
//    
//    private void createClassificationTask(Connection connection) throws JDMException {
//        BuildTaskFactory buildTaskFactory = (BuildTaskFactory)
//            connection.getFactory("javax.datamining.task.BuildTaskFactory");
//        BuildTask buildTask = buildTaskFactory.create("buildDataPhysicalDataSet",
//                "classificationSettings", "classificationModel");
//        connection.saveObject("classificationBuildTask", buildTask, false);
//    }
//    
//    private void executeClassificationTask(Connection connection) throws JDMException {
//        ExecutionHandle executionHandle = connection.execute("classificationBuildTask");
//        int timeoutInSeconds = 100;
//        ExecutionStatus executionStatus = executionHandle.waitForCompletion(timeoutInSeconds);
//        executionStatus = executionHandle.getLatestStatus();
//        if (ExecutionState.success.equals(executionStatus.getState())) {
//            //successful state
//        }
//    }
//    
//    private void retrieveClassificationModel(Connection connection)throws JDMException {
//        ClassificationModel classificationModel = (ClassificationModel)
//            connection.retrieveObject("classificationModel", NamedObject.model);
//        double classificationError = classificationModel.getClassificationError();
//    }
//    
//    private void testClassificationModel(Connection connection) throws JDMException {
//    	ClassificationTestTaskFactory testTaskFactory = (ClassificationTestTaskFactory)
//    		connection.getFactory("javax.datamining.supervised.classification.ClassificationTestTask");
//    	ClassificationTestTask classificationTestTask = testTaskFactory.create("testDataName", 
//    			"classificationModel", "testResultName");
//    	classificationTestTask.computeMetric(ClassificationTestMetricOption.confusionMatrix);
//    }
//    

}
