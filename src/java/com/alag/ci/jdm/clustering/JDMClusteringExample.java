package com.alag.ci.jdm.clustering;

import java.util.Collection;

//import javax.datamining.*;
//import javax.datamining.algorithm.kmeans.ClusteringDistanceFunction;
//import javax.datamining.algorithm.kmeans.KMeansSettings;
//import javax.datamining.algorithm.kmeans.KMeansSettingsFactory;
//import javax.datamining.clustering.*;
//import javax.datamining.resource.Connection;
//import javax.datamining.task.*;

public class JDMClusteringExample {

//    public void cluster(Connection connection ) throws JDMException {
//        createClusteringSettings(connection);
//        createClusteringTask(connection);
//        executeClusteringTask(connection);
//        retrieveClusteringModel(connection);
//    }
//    
//    private void createClusteringSettings(Connection connection) throws JDMException {
//        ClusteringSettingsFactory clusSettingsFactory = (ClusteringSettingsFactory)
//        connection.getFactory(
//                "javax.datamining.clustering.ClusteringSettingsFactory");
//        ClusteringSettings clusteringSettings = clusSettingsFactory.create();
//        clusteringSettings.setMaxNumberOfClusters(100);
//        clusteringSettings.setMinClusterCaseCount(10);
//        ClusteringAlgorithmSettings algorithmSettings = createKMeansClusteringSettings(connection);
//        clusteringSettings.setAlgorithmSettings(algorithmSettings);
//        connection.saveObject("clusteringSettings", clusteringSettings, false);
//    }
//    
//    private ClusteringAlgorithmSettings createKMeansClusteringSettings(Connection connection) throws JDMException {
//        KMeansSettingsFactory kmeansSettingsFactory = (KMeansSettingsFactory)
//        connection.getFactory(
//                "javax.datamining.algorithm.kmeans.KMeansSettingsFactory");
//        KMeansSettings kmeansSettings = kmeansSettingsFactory.create();
//        kmeansSettings.setDistanceFunction(ClusteringDistanceFunction.euclidean);
//        kmeansSettings.setMaxNumberOfIterations(100);
//        kmeansSettings.setMinErrorTolerance(0.001);
//        return kmeansSettings;
//    }
//    
//    private void createClusteringTask(Connection connection) throws JDMException {
//        BuildTaskFactory buildTaskFactory = (BuildTaskFactory)
//            connection.getFactory("javax.datamining.task.BuildTaskFactory");
//        BuildTask buildTask = buildTaskFactory.create("buildDataPhysicalDataSet",
//                "clusteringSettings", "clusteringModel");
//        connection.saveObject("clusteringBuildTask", buildTask, false);
//    }
//    
//    private void executeClusteringTask(Connection connection) throws JDMException {
//        ExecutionHandle executionHandle = connection.execute("clusteringBuildTask");
//        int timeoutInSeconds = 100;
//        ExecutionStatus executionStatus = executionHandle.waitForCompletion(timeoutInSeconds);
//        executionStatus = executionHandle.getLatestStatus();
//        if (ExecutionState.success.equals(executionStatus.getState())) {
//            //successful state
//        }
//    }
//    
//    private void retrieveClusteringModel(Connection connection)throws JDMException {
//        ClusteringModel clusteringModel = (ClusteringModel)
//            connection.retrieveObject("clusteringModel", NamedObject.model);
//        Collection<Cluster> clusters = clusteringModel.getClusters();
//        for (Cluster cluster: clusters) {
//            System.out.println(cluster.getClusterId() + " " + cluster.getName());
//        }
//    }
}
