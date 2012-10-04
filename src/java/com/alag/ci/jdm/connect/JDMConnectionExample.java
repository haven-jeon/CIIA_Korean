package com.alag.ci.jdm.connect;

import java.util.Hashtable;

//import javax.datamining.JDMException;
//import javax.datamining.resource.Connection;
//import javax.datamining.resource.ConnectionFactory;
//import javax.datamining.resource.ConnectionSpec;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JDMConnectionExample {
    private String userName = null;
    private String password = null;
    private String serverURI = null;
    private String providerURI = null;
    
    public JDMConnectionExample(String userName, String password,
            String serverURI, String providerURI) {
        this.userName = userName;
        this.password = password;
        this.serverURI = serverURI;
        this.providerURI = providerURI;
    } 
//    
//    public Connection createANewConnection() throws JDMException, NamingException {
//        ConnectionFactory connectionFactory = createConnectionFactory();
//        ConnectionSpec connectionSpec = getConnectionSpec(connectionFactory);
//        return connectionFactory.getConnection(connectionSpec);      
//    }
//    
//    public static void main(String [] args) throws Exception {
//        JDMConnectionExample eg = new JDMConnectionExample("username", "password", "serverURI",
//                "http://yourHost:yourPort/yourDMService");
//        Connection connection = eg.createANewConnection();   
//    }
//    
//    private ConnectionFactory createConnectionFactory() throws NamingException {
//        InitialContext initialJNDIContext = createInitialContext();
//        return (ConnectionFactory) initialJNDIContext.lookup("java:com/env/jdm/yourDMServer");
//    }
//
//    private InitialContext createInitialContext() throws NamingException {
//        Hashtable<String,String> environment= new Hashtable<String,String>();
//        environment.put(Context.INITIAL_CONTEXT_FACTORY, 
//                "com.your-company.javax.datamining.resource.initialContextFactory-impl");
//        environment.put(Context.PROVIDER_URL, this.providerURI);
//        environment.put(Context.SECURITY_PRINCIPAL, this.userName);
//        environment.put(Context.SECURITY_CREDENTIALS, this.password);
//        return new InitialContext(environment);
//    }
//
//    private ConnectionSpec getConnectionSpec(ConnectionFactory connectionFactory) {
//        ConnectionSpec connectionSpec = connectionFactory.getConnectionSpec();
//        connectionSpec.setName(this.userName);
//        connectionSpec.setPassword(this.password);
//        connectionSpec.setURI(this.serverURI);
//        return connectionSpec;
//    }


}
