package com.alag.ci.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alag.ci.MetaDataVector;
import com.alag.ci.TagMagnitude;
import com.alag.ci.impl.LogMetaDataVectorImpl;
import com.alag.ci.impl.MetaDataVectorImpl;
import com.alag.ci.impl.TagMagnitudeImpl;
import com.alag.ci.tagcloud.FontSizeComputationStrategy;
import com.alag.ci.tagcloud.TagCloud;
import com.alag.ci.tagcloud.VisualizeTagCloudDecorator;
import com.alag.ci.tagcloud.impl.HTMLTagCloudDecorator;
import com.alag.ci.tagcloud.impl.LinearFontSizeComputationStrategy;
import com.alag.ci.tagcloud.impl.TagCloudImpl;

import junit.framework.TestCase;


public class MetaDataVectorTest extends TestCase {
    private static final double PRECISION = 0.0001;
 
    /**
     * This test tests the dot product functionality
     * It creates two MetaDataVector and checks to make sure 
     * the magnitude of dot product is correct
     */
    public void testMetaDataVector() {
        List<TagMagnitude> l1 = new ArrayList<TagMagnitude>();
        List<TagMagnitude> l2 = new ArrayList<TagMagnitude>();
        l1.add(new TagMagnitudeImpl(0.75,1,"tag1"));
        l1.add(new TagMagnitudeImpl(0.5,2,"tag2"));
        l1.add(new TagMagnitudeImpl(0.25,3,"tag3"));
        l2.add(new TagMagnitudeImpl(0.25,1,"tag1"));
        l2.add(new TagMagnitudeImpl(0.5,2,"tag2"));
        
        MetaDataVectorImpl mdv1 = new MetaDataVectorImpl(l1);
        MetaDataVectorImpl mdv2 = new MetaDataVectorImpl(l2);
        //First test sort order for mdv2 it should be 2, 1 -- based on magnitude
        List<TagMagnitude> mdv2List = mdv2.getTagMetaDataMagnitude();
        TagMagnitude firstTag = mdv2List.get(0);
        assertTrue("Problem in sorting .. first element should be tag 2" ,
                 (firstTag.getTagId() == 2));
        
        //TestNormalization
        //sum of all magnitudes should be 1
        double magnitude = 0.;
        for (TagMagnitude tm : mdv2List) {
            magnitude += tm.getMagnitude()*tm.getMagnitude();
        }
        System.out.println("Magnitude is = " + magnitude);
        assertTrue("Magnitude of normalized tag should be 1", 
                Math.abs(magnitude-1)<PRECISION);
        
        //This should be 0.75*.25 + 0.5*0.5 = 7/16 = 
        double dotProduct = mdv1.dotProduct(mdv2);
        double answer = 0.83666;
        System.out.println("Dot product of \n" + mdv1 + "\n" + mdv2 + "\nis "
               + dotProduct + " expected " + answer);
        assertTrue("Dot product should be " + answer,
                Math.abs(answer-dotProduct) < PRECISION);
        
        //Dot product of a vector with itself should be 1
        dotProduct = mdv1.dotProduct(mdv1);
        answer = 1.;
        assertTrue("Dot product of a vector with itself should be 1",
                Math.abs(answer-dotProduct) < PRECISION);  
        
        //Distance of a vector from itself should be 0
        double distance = mdv1.distance(mdv1);
        answer = 0.;
        assertTrue("Dot product of a vector with itself should be 1",
                Math.abs(answer-distance) < PRECISION);        
    }
    
    /**
     * Test the example in chapter 2
     */
     public void testChapter2MetaDataVectorExample() {
         //Items
         MetaDataVector item1 = new MetaDataVectorImpl(getList1());
         MetaDataVector item2 = new MetaDataVectorImpl(getList2());
         MetaDataVector item3 = new MetaDataVectorImpl(getList3());        
         printDotProducts(item1,item2,item3);
         //Users
         MetaDataVector john = new MetaDataVectorImpl(getList4());
         MetaDataVector jane = new MetaDataVectorImpl(getList5());
         printDotProducts(item1,item2,item3,john,jane);
         //assertions
         assertTrue("Dot product of john should be 1", 
                 Math.abs(john.dotProduct(john)-1) < PRECISION);
         //Now the LogMetaDataVector
         System.out.println("\n\nLog ... ");
         item1 = new LogMetaDataVectorImpl(getList1());
         item2 = new LogMetaDataVectorImpl(getList2());
         item3 = new LogMetaDataVectorImpl(getList3());        
         printDotProducts(item1,item2,item3);
         //Users
         john = new LogMetaDataVectorImpl(getList4());
         jane = new LogMetaDataVectorImpl(getList5());
         printDotProducts(item1,item2,item3,john,jane);
         //assertions
         assertTrue("Dot product of john should be 1", 
                 Math.abs(john.dotProduct(john)-1) < PRECISION);
     }
     
     
     private  List<TagMagnitude> getList5() {
         List<TagMagnitude> l5 = new ArrayList<TagMagnitude>();
         l5.add(new TagMagnitudeImpl(1,2,"schema"));
         l5.add(new TagMagnitudeImpl(1,4,"database"));
         l5.add(new TagMagnitudeImpl(1,6,"normalized"));
         return l5;
     }
     
     private  List<TagMagnitude> getList4() {
         List<TagMagnitude> l4 = new ArrayList<TagMagnitude>();
         l4.add(new TagMagnitudeImpl(1,1,"tagging"));
         l4.add(new TagMagnitudeImpl(2,2,"schema"));
         l4.add(new TagMagnitudeImpl(1,3,"denormalized"));
         l4.add(new TagMagnitudeImpl(1,4,"database"));
         l4.add(new TagMagnitudeImpl(1,5,"binary"));
         return l4;
     }
     
     private  List<TagMagnitude> getList3() {
         List<TagMagnitude> l3 = new ArrayList<TagMagnitude>();
         l3.add(new TagMagnitudeImpl(1,1,"tagging"));
         l3.add(new TagMagnitudeImpl(4,2,"schema"));
         l3.add(new TagMagnitudeImpl(3,4,"database"));
         l3.add(new TagMagnitudeImpl(10,6,"normalized"));
         return l3;
     }
     
     private  List<TagMagnitude> getList2() {
         List<TagMagnitude> l2 = new ArrayList<TagMagnitude>();
         l2.add(new TagMagnitudeImpl(5,2,"schema"));
         l2.add(new TagMagnitudeImpl(8,4,"database"));
         l2.add(new TagMagnitudeImpl(5,5,"denormalized"));
         return l2;
     }
     
     private  List<TagMagnitude> getList1() {
         List<TagMagnitude> l1 = new ArrayList<TagMagnitude>();
         l1.add(new TagMagnitudeImpl(4,1,"tagging"));
         l1.add(new TagMagnitudeImpl(8,2,"schema"));
         l1.add(new TagMagnitudeImpl(6,3,"denormalized"));
         l1.add(new TagMagnitudeImpl(3,4,"database"));
         return l1;
     }
     
     private void printDotProducts(MetaDataVector item1, MetaDataVector item2,
             MetaDataVector item3, MetaDataVector john, MetaDataVector jane) {
         System.out.println("John=" + john);
         System.out.println("Jane=" + jane);
         
         double johnitem1 = john.dotProduct(item1);
         double johnitem2 = john.dotProduct(item2);
         double johnitem3 = john.dotProduct(item3);
         double janeitem1 = jane.dotProduct(item1);
         double janeitem2 = jane.dotProduct(item2);
         double janeitem3 = jane.dotProduct(item3);
         
         System.out.println("John=" + johnitem1 + "," + johnitem2 + "," + johnitem3);
         System.out.println("Jane=" + janeitem1 + "," + janeitem2 + "," + janeitem3);
         
     }
     
     private void printDotProducts(MetaDataVector item1, MetaDataVector item2,
             MetaDataVector item3) {
         System.out.println("Item1=" + item1);
         System.out.println("Item2=" + item2);
         System.out.println("Item3=" + item3);
         
         double item12 = item1.dotProduct(item2);
         double item13 = item1.dotProduct(item3);
         double item23 = item2.dotProduct(item3);
         System.out.println("Item12="+item12 + " Item13="+ item13 +
                 " Item23="+item23);
     }
     
     public void testChap3Example() {
         //Items
         MetaDataVector item1 = new MetaDataVectorImpl(getList6());
         MetaDataVector item2 = new MetaDataVectorImpl(getList7());
         MetaDataVector item3 = new MetaDataVectorImpl(getList8());  
         printDotProducts(item1,item2,item3);
     }

     private  List<TagMagnitude> getList6() {
         List<TagMagnitude> l6 = new ArrayList<TagMagnitude>();
         l6.add(new TagMagnitudeImpl(3,1,"u1"));
         l6.add(new TagMagnitudeImpl(2,2,"u2"));
         l6.add(new TagMagnitudeImpl(1,3,"u3"));
         return l6;
     }
     
     private  List<TagMagnitude> getList8() {
         List<TagMagnitude> l8 = new ArrayList<TagMagnitude>();
         l8.add(new TagMagnitudeImpl(2,1,"u1"));
         l8.add(new TagMagnitudeImpl(4,2,"u2"));
         l8.add(new TagMagnitudeImpl(5,3,"u3"));
         return l8;
     }
     
     private  List<TagMagnitude> getList7() {
         List<TagMagnitude> l7 = new ArrayList<TagMagnitude>();
         l7.add(new TagMagnitudeImpl(4,1,"u1"));
         l7.add(new TagMagnitudeImpl(2,2,"u2"));
         l7.add(new TagMagnitudeImpl(3,3,"u3"));
         return l7;
     }
     
     public void testTagCloud() {
         int numSizes = 3;
         String fontPrefix = "font";
         MetaDataVector item1 = new MetaDataVectorImpl(getList1());
         FontSizeComputationStrategy strategy = 
             new LinearFontSizeComputationStrategy(numSizes,fontPrefix);
         TagCloud tagCloud = new TagCloudImpl(item1,strategy);
         System.out.println(tagCloud);
         //Users
         MetaDataVector john = new MetaDataVectorImpl(getList4());
         MetaDataVector jane = new MetaDataVectorImpl(getList5());
         TagCloud johnCloud = new TagCloudImpl(john,strategy);
         System.out.println(johnCloud);
         TagCloud janeCloud = new TagCloudImpl(jane,strategy);
         System.out.println(janeCloud);
     }
     
     public void testMetaDataVectorAdd() {
         //Users
         MetaDataVector john = new MetaDataVectorImpl(getList4());
         MetaDataVector jane = new MetaDataVectorImpl(getList5());
         System.out.println("John=" + john);
         System.out.println("Jane=" + jane);
         //Merged vector with equal weights
         MetaDataVector mergedVector = john.add(jane,1);
         System.out.println("Merged=" + mergedVector);
         double johnDotProduct = john.dotProduct(mergedVector);
         System.out.println("johnDotProduct=" + johnDotProduct);
         double janeDotProduct = jane.dotProduct(mergedVector);
         System.out.println("janeDotProduct=" + janeDotProduct);
         assertTrue("The new vector should be equidistant with the other two "
                 + johnDotProduct + " != " + janeDotProduct,
                 Math.abs(janeDotProduct-johnDotProduct)< PRECISION);
     }
     
     public void testVisualizeMetaDataVector() throws Exception {
         MetaDataVector johnMDV = new MetaDataVectorImpl(getList4());
         FontSizeComputationStrategy strategy = 
             new LinearFontSizeComputationStrategy(3,"font-size: ");
         TagCloud tagCloud = new TagCloudImpl(johnMDV,strategy);
         System.out.println(tagCloud);
         writeToFile("johnTagCloud.html",tagCloud);
     }
     
     private static void writeToFile(String fileName, TagCloud cloud) 
         throws IOException {
         BufferedWriter out = new BufferedWriter(
                 new FileWriter(fileName));
         VisualizeTagCloudDecorator decorator = new HTMLTagCloudDecorator();
         out.write(decorator.decorateTagCloud(cloud));
         out.close();
     }
     public static void main(String [] args) throws Exception {
         MetaDataVectorTest test = new MetaDataVectorTest();
         test.testVisualizeMetaDataVector();
        
     }
     
 
}
