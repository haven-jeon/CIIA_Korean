package com.alag.ci.webcrawler.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alag.ci.webcrawler.CrawlerUrl;
import com.alag.ci.webcrawler.NaiveCrawler;

import junit.framework.TestCase;

public class NaiveCrawlerTest extends TestCase {
    private static final String MANNING_URL = "http://www.manning.com";
    private static final String WIKIPEDIA_URL = "http://www.wikipedia.org";
    private static final String WIKIPEDIA_CI_URL =
        "http://www.wikipedia.org/wiki/Collective+intelligence";
    private static final String AMAZON_URL = "http://www.amazon.com";
    private static final String REGEX_CI = "collective.*intelligence";
    private static final String REGEX_BOOK = "book";
    private static final String REGEXP_RELATIVE =
        "<a href=\"/((\\w)+(/))?(\\w)+(.html)?(.htm)?(/[\\w\\.\"]+)?";
        
    //"<a href=\"/((\\w)+(/))+(\\w)+(.html)?(/[&?               \\+\\%/\\.\\w]+)?(\">)?";
    private static final String REL_URL1 =
        "<a href=\"/links/index.html#soon\">";
    private static final String REL_URL2 =
        "<a href=\"/catalog/java\">"; 
    private static final String REL_URL3 =
        "<a href=\"/academic\">";
    
    private NaiveCrawler getCrawler(String seedUrl, String regexp) throws Exception {
        Queue<CrawlerUrl> urlQueue = new LinkedList<CrawlerUrl>();
        urlQueue.add(new CrawlerUrl(seedUrl,0));
        return new NaiveCrawler(urlQueue,5,5,5000L,regexp);
    }
    
    private NaiveCrawler getCrawler() throws Exception {
        return getCrawler("", "");
    }

    public void testAreWeAllowedToVisit() throws Exception{
        NaiveCrawler crawler = getCrawler();
        boolean flag = areWeAllowedToVisit(crawler,MANNING_URL);
        assertTrue("We should be able to crawl " + MANNING_URL, flag);
        String url = MANNING_URL + "/_mm/";
        flag = areWeAllowedToVisit(crawler,url);
        assertTrue("We should not be able to crawl " + url, !flag);  
        url = MANNING_URL + "/_mm/foo";
        flag = areWeAllowedToVisit(crawler,url);
        assertTrue("We should not be able to crawl " + url, !flag);  
        flag = areWeAllowedToVisit(crawler,WIKIPEDIA_URL);
        assertTrue("We should be able to crawl " + WIKIPEDIA_URL, flag);
        flag = areWeAllowedToVisit(crawler,WIKIPEDIA_CI_URL);
        assertTrue("We should be able to crawl " + WIKIPEDIA_CI_URL, flag);
        flag = areWeAllowedToVisit(crawler,WIKIPEDIA_URL+"/trap/");
        assertFalse("We should not be able to crawl " 
                + WIKIPEDIA_URL+"/trap/", flag);
        flag = areWeAllowedToVisit(crawler,AMAZON_URL);
        assertTrue("We should be able to crawl " + AMAZON_URL, flag);
        String notAllowed = AMAZON_URL + "/gp/flex";
        flag = areWeAllowedToVisit(crawler,notAllowed);
        assertFalse("We should be able to crawl " + notAllowed, flag);
    }
    
    private boolean areWeAllowedToVisit(NaiveCrawler crawler, String url) {
        CrawlerUrl crawlerUrl = new CrawlerUrl(url,0);
        return crawler.doWeHavePermissionToVisit(crawlerUrl);
    }
    
    public void testIsContentRelevant() {
        Pattern p = Pattern.compile(REGEX_BOOK);
        String text = " this has book in";
        boolean flag = NaiveCrawler.isContentRelevant(text, p);
        assertTrue(REGEX_BOOK + " is relevant to " + text, flag);
        p = Pattern.compile(REGEX_CI);
        text = " this has book in";
        flag = NaiveCrawler.isContentRelevant(text, p);
        assertFalse(REGEX_CI + " is not relevant to " + text, flag);
        text = "http://junk.com/collective+intelligence/foo.h";
        flag = NaiveCrawler.isContentRelevant(text, p);
        assertTrue(REGEX_CI + " is  relevant to " + text, flag);
        text = "<title>Collective+intelligence - Wikipedia, the free encyclopedia</title>";
        flag = NaiveCrawler.isContentRelevant(text, p);
        assertTrue(REGEX_CI + " is  relevant to " + text, flag);
    }
    
    public void testRelativeUrlRegex() {
        Pattern p = Pattern.compile(REGEXP_RELATIVE);
        Matcher m = p.matcher(REL_URL1);
//        boolean retValue = m.find();
//        System.out.println(retValue);
        printMatches(m);
        m = p.matcher(REL_URL2);
//        retValue = m.find();
        printMatches(m);
//        System.out.println(retValue);
        m = p.matcher(REL_URL3);
        printMatches(m);
    }
    
    private void printMatches(Matcher m) {
        while (m.find()) {
            String text = m.group();
            System.out.println(text);
            String [] paths = text.split("<a href=\"");
            for (String path : paths) {
                String filPath = path;
//                if (filPath.endsWith("\">")) {
//                    filPath = filPath.substring(0, filPath.length() - 2);
//                }
                System.out.println(filPath);
            }
        }
    }
    
    public void testContentParsing() {
        String s1 = "<a href=\"http://www.amazon.com/gp/blog/id/A368TUB0Q1IE3F/rss.xml/ref=cm_blog_rss_b/002-5048096-6493656\">Dierk Koenig</a>";
        String s2 = "<a href=\"http://ui.constantcontact.com/d.jsp?m=1101335703814&p=oi\"><img src=\"/images/mail_list.gif\"></a>";
        List<String> list = detectHttpUrls(s1);
        String url = "http://www.amazon.com/gp/blog/id/A368TUB0Q1IE3F/rss.xml/ref=cm_blog_rss_b/002-5048096-6493656";
        assertTrue(s1 + " not parsed correctly " + list, isStringPresent(url,list));
        list = detectHttpUrls(s2);
        url = "http://ui.constantcontact.com/d.jsp?m=1101335703814&p=oi";
        assertTrue(s1 + " not parsed correctly " + list, isStringPresent(url,list));
    }
    
    private boolean isStringPresent(String s, List<String> urls) {
        for (String url: urls) {
            System.out.println("url = " + url);
            if (url.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    private List<String> detectHttpUrls(String text) {
        Pattern httpRegexp = Pattern.compile(NaiveCrawler.REGEXP_HTTP);
        List<String> parsedUrls = new ArrayList<String>();
        Matcher m = httpRegexp.matcher(text);
        while (m.find()) {
            String url = m.group();
            String [] terms = url.split("a href=\"");
            for (String term: terms) {
                System.out.println("Term = " + term);
                if (term.startsWith("http")) {
                    int index = term.indexOf("\"");
                    if (index > 0) {
                        term = term.substring(0,index);
                    }
                    parsedUrls.add(term);
                } 
            }
        }
        return parsedUrls;
    }
    
//    private List<String> detectAltUrls(String text) {
//        String s = "<a href=\"http://";
//    }
    
    
    public static void main(String [] args) {
        NaiveCrawlerTest test = new NaiveCrawlerTest();
        test.testRelativeUrlRegex();
    }
}
