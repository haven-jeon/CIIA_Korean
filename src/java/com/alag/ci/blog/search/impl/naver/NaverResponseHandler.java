package com.alag.ci.blog.search.impl.naver;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.alag.ci.blog.search.XmlToken;
import com.alag.ci.blog.search.impl.BlogSearchResponseHandlerImpl;
import com.alag.ci.blog.search.impl.RetrievedBlogEntryImpl;

public class NaverResponseHandler extends BlogSearchResponseHandlerImpl {
/*
 * 
 * <rss version="2.0">
<channel>
 <title>Naver Open API - blog ::'ipad'</title>
 <link>http://search.naver.com</link>
 <description>Naver Search Result</description>
 <lastBuildDate>Sun, 18 Apr 2010 19:11:19 +0900</lastBuildDate>
 <total>11675</total>
 <start>1</start>
 <display>10</display>
<item>
 <title><b>iPad</b> �����е� �ڽ� ���� ����.. - Apple <b>iPad</b></title>
 <link>http://openapi.naver.com/l?AAACupLEi1TcrJT1crzUyxNTMxMTRTKyxNLaq0zSxITFHLKEpNs80oKSlQNXZUNXIDIpBavbzEstQiveT8XKBAVmZetomhoaqxW1BqSmZRanKJqrGLT366qpEZUKlfPpBnaGpgYGFsYQ4kLQFQvLnJcQAAAA==</link>
 <description>... ���� ���⸦ �ٷ����ϴ�... �̿� �����Ͻð�... <b>iPad</b> �����߸Ÿ� ���� ������ü���� ��~�� ������� ���ٸ�... </description>
 <bloggername>�йٰ� I'm real</bloggername>
 <bloggerlink>http://blog.naver.com/jink411</bloggerlink>
 </item>
<item>
 <title><b>iPad</b> App�� ������?</title>
 <link>http://openapi.naver.com/l?AAACupLEi1TcrJT1crzUyxNTMxMTRTKyxNLaq0zSxITFHLKEpNs80oKSlQNXZUNXIDosyknNIivZLM4pL8okq95PxcoJilOQBHaUdaRwAAAA==</link>
 <description><b>iPad</b> App�� ������ iPhone App�� �ι��� ���� ����. (<b>iPad</b> App Store�� Screen shot�� ������... </description>
 <bloggername>Storage. it -</bloggername>
 <bloggerlink>iblur.tistory.com</bloggerlink>
 </item>
<item>
 <title><b>IPad</b> ���� ����Ʈ</title>
 <link>http://openapi.naver.com/l?AAAB2M2wqEIBRFv0YfZTqaTQ8+VMz8R9lpEro4ehxwvj4JNixYCzZlj2bazg9PbjZaqUrzb8KQjfPjzNeAi1mJPJMdg3fZnoKjfxbRjxaj2NwPhT33UvryUmCPyKCGSuq+la8O2uEBQ9PoW4J61gIPCvkC0aOohnkAAAA=</link>
 <description>... your <b>iPad</b>.Wi-Fi16GB1Ships: Delivers on April 3rd Free Shipping $499.00 Pre-Order <b>iPad</b> with Wi-Fi... </description>
 <bloggername>Muritzy</bloggername>
 <bloggerlink>muritzy.spaces.live.com</bloggerlink>
 </item>
<item>
 <title>Apple NEW <b>IPAD</b> ( ���� �����е�)</title>
 <link>http://openapi.naver.com/l?AAACupLEi1TcrJT1crzUyxNTMxMTRTKyxNLaq0zSxITFHLKEpNs80oKSlQNXZUNXIDIpBavbzEstQiveT8XKBAeU4hEJkZm5iqGrsFpaZkFqUml6gau/jkp6samQFV++UDecYGBhbG5uYWhsaWAHJR7ldzAAAA</link>
 <description>... �̱��� ���� <b>Ipad</b> �� ��� �Ǿ����ϴ� ^^ �ٵ�... ���ʹ� �Ȼ�� �־�����, <b>Ipad</b> �� ���Դٱ淡 �̸�... </description>
 <bloggername>�����ູ�� ���󿡼� ����ϴ� �׿� ������ ��</bloggername>
 <bloggerlink>http://blog.naver.com/wlqwlq6345</bloggerlink>
 </item>
<item>
 <title>��ϺϽ����� <b>iPad</b>�� ������</title>
 <link>http://openapi.naver.com/l?AAACupLEi1TcrJT1crzUyxNTMxMTRTKyxNLaq0zSxITFHLKEpNs80oKSlQNXZUNXIDouwivdS89MSU9NQSveT8XKCIkYGhAZAyMAYRFkACpFO3AKiqNDPPCMgHAFXJOnRkAAAA</link>
 <description>... ��ϺϽ����� <b>iPad</b>�� ������1 day �� JINQ... Peguin Books�� �̹����� <b>iPad</b>�� ����å�� ������... </description>
 <bloggername>Engadget Korea</bloggername>
 <bloggerlink>kr.engadget.com</bloggerlink>
 </item>
<item>
 <title>���� ������� ������ <b>iPad</b> - <b>iPad</b> Arcade Cabinet</title>
 <link>http://openapi.naver.com/l?AAACupLEi1TcrJT1crzUyxNTMxMTRTKyxNLaq0zSxITFHLKEpNs80oKSlQNXZUNXIDouS85MyU1MRivZLM4pL8okq95PxcoLCJpSUAcPpE1UsAAAA=</link>
 <description>... <b>iPad</b> Arcade Cabinet�� <b>iPad</b>�� ���� ������� ���Ž��� �� ����ִ� �ֺ�����Դϴ�.10��ġ�� Ŀ�� <b>iPad</b>... </description>
 <bloggername>Cool & Creative Ideas</bloggername>
 <bloggerlink>cncideas.tistory.com</bloggerlink>
 </item>
<item>
 <title>iCade for <b>iPad</b></title>
 <link>http://openapi.naver.com/l?AAACupLEi1TcrJT1crzUyxNTMxMTRTKyxNLaq0zSxITFHLKEpNs80oKSlQNXZUNXIDovLycr3kyvL8opwUveT8XKCIUXJ+fg4IA9nGpgYWlgamAL9AVWdWAAAA</link>
 <description><b>iPad</b>��Ī���� ���� hot�� <b>iPad</b> �Ǽ��縮�� ���Դ�!! �̸��Ͽ� <b>iPad</b> Acade Cabinet! iCade!! ����... </description>
 <bloggername>2coolcool @ u</bloggername>
 <bloggerlink>www.cyworld.com/2coolcool</bloggerlink>
 </item>
<item>
 <title><b>iPad</b> Gets a Split Screen Browser</title>
 <link>http://openapi.naver.com/l?AAABWLOwrDMBAFT2OVAifGnQo3uYbQ58USONFmtc7i20eBKYaBkYvg4tF2c9bs1mWZV/M5wZerFLIpjKcrIjTdt+n2GKiqZYSsXAWKaFN7jRw4lfpFH/of/Q7pPvhORxXfEwNvH7lpB1sq9ANKoxx0dwAAAA==</link>
 <description>... first version of the <b>iPad</b> doesn't support app... open at once on the <b>iPad</b>.SponsorIt's little... </description>
 <bloggername>ReadWriteWeb</bloggername>
 <bloggerlink>www.readwriteweb.com</bloggerlink>
 </item>
<item>
 <title>�۽��� ��ǻ���� ��ȭ, <b>iPad</b></title>
 <link>http://openapi.naver.com/l?AAAA3JMQ6AIAwAwNfISEQJWwcX/6FQpYkIljLwe0luO+kF4XzyrRoFcNYap76G3IHKEVRkvCCKlGndpmUfUqvk6a0UkLVQlcxd+5xGGTf/O8bDXU8AAAA=</link>
 <description>... ������ �غ��� ���� <b>iPad</b>�� ���õ�... we really wanted for the <b>iPad</b>, and RTSes and... </description>
 <bloggername>music insider</bloggername>
 <bloggerlink>musicinsider.tistory.com</bloggerlink>
 </item>
<item>
 <title>Is the <b>iPad</b> Magical? Our First Impressions</title>
 <link>http://openapi.naver.com/l?AAABWLQQqEMAwAX2OPhVXx1sNe9htSbbSB1cYkGvy9FeYwDIzeBGH6l9WdmMLQ95/BHSfwHZBicplhCVmVmu7btL+KmXmGmIxRwWDyc9lqjjxnvECqvuO4IIuOuBGDCJZdPGV6AKzP0xRtAAAA</link>
 <description>... the apps, hardware and <b>iPad</b>-optimized web sites... time with the <b>iPad</b>.SponsorIt's Very Fast,... </description>
 <bloggername>ReadWriteWeb</bloggername>
 <bloggerlink>www.readwriteweb.com</bloggerlink>
 </item>
 </channel>
 </rss>
 * 
 */
	
	public enum NaverXmlToken implements XmlToken {
		ITEM("item"), TITLE("title"), LINK("link"), DESC("description"), BLOGGERNAME("bloggername"),
		BOGGERLINK("bloggerlink"), LASTBUILDDATE("lastBuildDate"), TOTAL("total"), CHANNEL("channel");

		private String tag = null;
		
		NaverXmlToken(String tag){
			this.tag = tag;
		}
		@Override
		public String getTag() {
			// TODO Auto-generated method stub
			return this.tag;
		}
	}

	

	@Override
	public void characters(char[] buf, int offset, int len) throws SAXException {
		// TODO Auto-generated method stub
		String s = this.getCharString() +  new String(buf, offset, len);
		this.setCharString(s);

		NaverXmlToken token = (NaverXmlToken)this.getWhichToken();
		
		if(token == NaverXmlToken.TOTAL){
			this.getResult().setQueryCount(new Integer(s));
			return;
		}
		
		RetrievedBlogEntryImpl item = getItem();
		if ((token != null) && (item != null) ){
			switch(token){
				case BOGGERLINK:{
					item.setName(s);
					break;
				}
				case LINK:{
					item.setUrl(s);
					break;
				}
				case TITLE:{
					item.setTitle(s);
					break;
				}
				case BLOGGERNAME:{
					item.setAuthor(s);
					break;
				}
				case DESC:{
					item.setExcerpt(s);
					break;
				}
			}
		}
	}

	@Override
	protected XmlToken[] getXMLTokens() {
		// TODO Auto-generated method stub
		return NaverXmlToken.values();
	}

	@Override
	protected boolean isBlogEntryToken(XmlToken t) {
		// TODO Auto-generated method stub
		return (NaverXmlToken.ITEM.compareTo(
				(NaverXmlToken)t) == 0);
	}

}
