package com.alag.ci.test;

import java.util.ArrayList;
import java.util.List;

//import org.apache.commons.httpclient.*;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.params.HttpMethodParams;

import com.alag.ci.RatingMagnitude;
import com.alag.ci.RatingVector;
import com.alag.ci.impl.RatingMagnitudeImpl;
import com.alag.ci.impl.RatingVectorImpl;

import junit.framework.TestCase;

public class RatingVectorTest extends TestCase {
    
//    public void testHttpClient() {
//        String url = "http://search.yahoo.com/search";
//  
//        try {
//            HttpClient client = new HttpClient();
//            PostMethod method = new PostMethod(url);    
//            //Configure form parameters
//            method.addParameter("p", "Java");
//            int statusCode = client.executeMethod(method);
//            System.out.println("Status = " + statusCode);
//            if (statusCode != 1) {
//                String contents = method.getResponseBodyAsString();
//                method.releaseConnection();
//                System.out.println(contents);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    
//    public void testDigg() {
//        String url = "http://digg.com/submit";
//        
//        try {
//            HttpClient client = new HttpClient();
//            PostMethod method = new PostMethod(url);  
//            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
//                    new DefaultHttpMethodRetryHandler(3,false));
//
//            //Configure form parameters
//            method.addParameter("phase", "2");
//            method.addParameter("url",
//                    "http://utilitycomputing.itworld.com/4594/061201bonsite/page_1.html");
//            method.addParameter("title","Title");
//            method.addParameter("bodytext","Text");
//            method.addParameter("topic","tech_news");
//            int statusCode = client.executeMethod(method);
//            System.out.println("Status = " + statusCode);
//            if (statusCode != HttpStatus.SC_OK) {
//                System.err.println("Method failed: " + method.getStatusLine());
//              }
//
//            String redirectLocation = "";
//            Header locationHeader = method.getResponseHeader("location");
//            if (locationHeader != null) {
//                redirectLocation = locationHeader.getValue();
//            } else {
//                // The response is invalid and did not provide the new location for
//                // the resource.  Report an error or possibly handle the response
//                // like a 404 Not Found error.
//            }
//            System.out.println("redirectLocation="+redirectLocation);
//            
//            if (statusCode != 1) {
//                String contents = method.getResponseBodyAsString();
//                method.releaseConnection();
//                System.out.println(contents);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    public void testChapter3Example() {       
        List<RatingMagnitude> item1 = new ArrayList<RatingMagnitude>();
        item1.add(new RatingMagnitudeImpl(1,3,3));
        item1.add(new RatingMagnitudeImpl(2,2,8./3.));
        item1.add(new RatingMagnitudeImpl(3,1,3));
        RatingVector rv1 = new RatingVectorImpl(RatingVector.OwnerType.ITEM,item1);
        
        List<RatingMagnitude> item2 = new ArrayList<RatingMagnitude>();
        item2.add(new RatingMagnitudeImpl(1,4,3));
        item2.add(new RatingMagnitudeImpl(2,2,8./3.));
        item2.add(new RatingMagnitudeImpl(3,3,3));
        RatingVector rv2 = new RatingVectorImpl(RatingVector.OwnerType.ITEM,item2);
        
        List<RatingMagnitude> item3 = new ArrayList<RatingMagnitude>();
        item3.add(new RatingMagnitudeImpl(1,2,3));
        item3.add(new RatingMagnitudeImpl(2,4,8./3.));
        item3.add(new RatingMagnitudeImpl(3,4,3));
        RatingVector rv3 = new RatingVectorImpl(RatingVector.OwnerType.ITEM,item3);
        
        System.out.println(rv1+"\n"+rv2+"\n"+rv3);
        
        System.out.println("corr(1,2)=" + rv1.getCorrelation(rv2)+
                " sim(1,2)=" + rv1.getItemAdjustedCosineSimilarity(rv2));
        System.out.println("corr(1,3)=" + rv1.getCorrelation(rv3)+
                " sim(1,3)=" + rv1.getItemAdjustedCosineSimilarity(rv3));
        System.out.println("corr(2,3)=" + rv2.getCorrelation(rv3)+
                " sim(2,3)=" + rv2.getItemAdjustedCosineSimilarity(rv3));
        System.out.println("corr(2,3)=" + rv3.getCorrelation(rv2)+
                " sim(3,2)=" + rv3.getItemAdjustedCosineSimilarity(rv2));
        System.out.println("corr(2,2)=" + rv2.getCorrelation(rv2)+
                " sim(2,2)=" + rv2.getItemAdjustedCosineSimilarity(rv2));  
        
        //Users
        List<RatingMagnitude> user1 = new ArrayList<RatingMagnitude>();
        user1.add(new RatingMagnitudeImpl(1,3,3));
        user1.add(new RatingMagnitudeImpl(2,4,3));
        user1.add(new RatingMagnitudeImpl(3,2,3));
        rv1 = new RatingVectorImpl(RatingVector.OwnerType.USER,user1);
        
        List<RatingMagnitude> user2 = new ArrayList<RatingMagnitude>();
        user2.add(new RatingMagnitudeImpl(1,2,8./3.));
        user2.add(new RatingMagnitudeImpl(2,2,8./3.));
        user2.add(new RatingMagnitudeImpl(3,4,8./3.));
        rv2 = new RatingVectorImpl(RatingVector.OwnerType.USER,user2);
        
        List<RatingMagnitude> user3 = new ArrayList<RatingMagnitude>();
        user3.add(new RatingMagnitudeImpl(1,1,3));
        user3.add(new RatingMagnitudeImpl(2,3,3));
        user3.add(new RatingMagnitudeImpl(3,5,3));
        rv3 = new RatingVectorImpl(RatingVector.OwnerType.USER,user3);
        
        System.out.println(rv1+"\n"+rv2+"\n"+rv3);
        
        System.out.println("corr(1,2)=" + rv1.getCorrelation(rv2)+
                " sim(1,2)=" + rv1.getItemAdjustedCosineSimilarity(rv2));
        System.out.println("corr(1,3)=" + rv1.getCorrelation(rv3)+
                " sim(1,3)=" + rv1.getItemAdjustedCosineSimilarity(rv3));
        System.out.println("corr(2,3)=" + rv2.getCorrelation(rv3)+
                " sim(2,3)=" + rv2.getItemAdjustedCosineSimilarity(rv3));
        System.out.println("corr(2,3)=" + rv3.getCorrelation(rv2)+
                " sim(3,2)=" + rv3.getItemAdjustedCosineSimilarity(rv2));
        System.out.println("corr(2,2)=" + rv2.getCorrelation(rv2)+
                " sim(2,2)=" + rv2.getItemAdjustedCosineSimilarity(rv2));     

    }

}
