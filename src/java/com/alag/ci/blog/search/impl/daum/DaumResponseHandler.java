package com.alag.ci.blog.search.impl.daum;

import org.xml.sax.SAXException;

import com.alag.ci.blog.search.XmlToken;
import com.alag.ci.blog.search.impl.BlogSearchResponseHandlerImpl;
import com.alag.ci.blog.search.impl.RetrievedBlogEntryImpl;
import com.alag.ci.blog.search.impl.naver.NaverResponseHandler.NaverXmlToken;

public class DaumResponseHandler extends BlogSearchResponseHandlerImpl {
	/*
	 * <channel>
 <title>Search blog Daum Open API</title>
 <desc>Daum Open API search result</desc>
 <totalCount>10155</totalCount>
 <result>10</result>
 <sort>accu</sort>
 <q>ipad</q>
 <pageno>1</pageno>
<item>
 <title><b>iPad</b>, and iPhone OS 4</title>
 <description>�����ϰڴٸ�.) ��� �� ��α� �۵� �̹� �ָ����� ����� �� �б����θ� �ϴٰ� �߰��� ���鼭 ���� �����ߴ�. <b>iPad</b>. �ϴ�, �����е���� ��������. �����е忡 ���� �� ������ ������ iAppBox ���信�� Ȯ���� �� �ִ�. ����...</description>
 <link>http://kudol.pe.kr/1484</link>
 <author>NHS Story: A New Beginning</author>
 <comment>http://kudol.pe.kr</comment>
 <pubDate>20100422104409</pubDate>
 </item>
<item>
 <title>22��(��) ������ iPhone & <b>iPad</b> �ֽ� �����ҽ� ����</title>
 <description>iPhone & <b>iPad</b> Ʈ���ͷε� �ٷ� ���並 ���Ǽ��� �ֽ��ϴ�. 22��(��) ������ iPhone & <b>iPad</b> �ֽ� �����ҽ� ���� AT&T ���� ���� ���� �����ơ������� ȿ�� AT&T�� 21��(�����ð�) ���� 1�б� �������� ������ ���� �ñ⿡ ���� 21% ��...</description>
 <link>http://qswoo.tistory.com/905</link>
 <author>iPhone �� ����� �̷������ ���� �ʷ���37�� ������α�</author>
 <comment>http://qswoo.tistory.com</comment>
 <pubDate>20100422094518</pubDate>
 </item>
<item>
 <title>21��(��) ������ iPhone & <b>iPad</b> �ֽ� �����ҽ� ����</title>
 <description>iPhone & <b>iPad</b> Ʈ���ͷε� �ٷ� ���並 ���Ǽ��� �ֽ��ϴ�. 21��(��) ������ iPhone & <b>iPad</b> �ֽ� �����ҽ� ���� `�ź�����` ������ ������ ������ ���� ����? ����ǰ�� ����ϱ� �� ö���� �������� ������ ������ ������ ����������...</description>
 <link>http://qswoo.tistory.com/900</link>
 <author>iPhone �� ����� �̷������ ���� �ʷ���37�� ������α�</author>
 <comment>http://qswoo.tistory.com</comment>
 <pubDate>20100421093354</pubDate>
 </item>
<item>
 <title>�����е��, <b>iPad</b> DJ ����</title>
 <description>'��������' �� ���� �ı޷��� ���� �� �������� �������̳�(������ �ǰ�) ��·�� 'GIZMODO' ������ �׳ฦ [<b>IPad</b> DJ] �� �Ұ��ߴ�. [<b>iPad</b> DJ]�� �Ұ��� �׳��� �̸��� ��Rona Sobhany��. �׳�� �� ���� �����е带 Numark M3 2ch...</description>
 <link>http://www.soulog.com/19</link>
 <author>www.soulog.com</author>
 <comment>http://www.soulog.com</comment>
 <pubDate>20100422101308</pubDate>
 </item>
<item>
 <title><b>iPad</b>�� ���� �˾� �ΰ� ���� ��� �͵�</title>
 <description>��� ������ ȭ���� �ǰ� �ִ� ������ ����ǰ <b>iPad</b>. ���ڴ� ����, �ؿ�������������, ��� ���� �� �ð� ����� ��ȸ�� �����. ����� ����, Wi-Fi������ �޸���� �Ƹ����. ������ ����Ʈ�� ����ǰ� �ִ� ������ �� �������...</description>
 <link>http://bastof.tistory.com/648</link>
 <author>Bastof �� �����̾߱� - Be A Stranger to Fear!</author>
 <comment>http://bastof.tistory.com</comment>
 <pubDate>20100413141049</pubDate>
 </item>
<item>
 <title><b>iPad</b> ���ø����̼�, ��ȭ����� ������ ���� ���뿡��...</title>
 <description>Key Message - �̱��� ��ȭ ���� ������� <b>iPad</b> ���ø����̼ǿ� ���� �ð����� �巯���� �ִ�. �渮��� ��ȭ����� ���, iTunes ���� ���� ����ä�ΰ� ������ ���� �� �ϳ��� �Ǹ� ä���� ���� ������ ���ٴ� �����̾ <b>iPad</b> ����...</description>
 <link>http://blog.naver.com/zvezda/130084657553</link>
 <author>FutureProof of Digital World</author>
 <comment>http://blog.naver.com/zvezda</comment>
 <pubDate>20100421010934</pubDate>
 </item>
<item>
 <title><b>iPad</b> vs. Kindle: Who's the better e-Reader?</title>
 <description>The <b>iPad</b> is indeed a useful, entertaining, engaging and disruptive product, but is it really a better reading device than the traditional e-Ink readers currently on the market? We put the <b>iPad</b>��s backlit IPS LCD...</description>
 <link>http://blogs.zdnet.com/perlow/?p=12719&utm_source=feedburner&utm_medium=feed&utm_campaign=Feed%3A+ZDNetBlogs+%28ZDNet+All+Blogs%29</link>
 <author/>
 <comment>http://blogs.zdnet.com</comment>
 <pubDate>20100421131538</pubDate>
 </item>
<item>
 <title>�����е� Ű��Ʈ ����ϱ� Keynote for <b>iPad</b></title>
 <description>���� Ű��Ʈ��, ���û翡�� ���� ��ǥ�� ����Ʈ�����Դϴ�. MS Office�� ���Ե� PowerPoint�� ���� ������ �մϴ�. �Ŀ�����Ʈ�� �� ������ ���� ���� ���ϰ� �ۼ��� �� �ִ� ������ �ִ� �ݸ�, ���� Ű��Ʈ�� �پ���/������...</description>
 <link>http://bahnsville.tistory.com/339</link>
 <author>BAHNsville</author>
 <comment>http://bahnsville.tistory.com</comment>
 <pubDate>20100415105939</pubDate>
 </item>
<item>
 <title>���� ������ ���� �����е�(<b>iPad</b>), ������ ���ߴ�.</title>
 <description>���� �ۿ��� ���� ��� ���� ���� �����е�(<b>iPad</b>)�� ��ó��� ������ ��ȸ�� �־����ϴ�. ���������� ���������� ��ǳ�� �ҷ�����Ų ������ �߽����� ������ ��ǰ����. ���� �츮���� ���� ��õ����� �ʾ����� ���������� ���ø�...</description>
 <link>http://lazion.com/2511925</link>
 <author>������ LAZION.com</author>
 <comment>http://lazion.com</comment>
 <pubDate>20100414072254</pubDate>
 </item>
<item>
 <title>[<b>iPad</b>] �����е��� �ñ����� Ǯ�� ����, �����е�vs...</title>
 <description>������ ������ ������� IT�迡 �� �ٶ��� �ϰ� �ֽ��ϴ�. �������� ���� ���ÿ��� �����е�(<b>iPad</b>)�� �� ���Ұ�, ���� �ѱ������� ��� ���̶� �������� �̾� �����е��� �������ο����� ���ݿ����� ���� �ɷ� �˰� �ֽ��ϴ�. SONY ...</description>
 <link>http://www.yesbedesign.com/198</link>
 <author>������ �����δ���</author>
 <comment>http://www.yesbedesign.com</comment>
 <pubDate>20100419215229</pubDate>
 </item>
 </channel>
	 */
	public enum DaumXmlToken implements XmlToken {
		ITEM("item"), TITLE("title"), LINK("link"), DESC("description"), BLOGGERNAME("author"),
		PUBDATE("pubDate"), TOTAL("totalCount"), CHANNEL("channel"), COMMENT("comment");

		private String tag = null;
		
		DaumXmlToken(String tag){
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

		DaumXmlToken token = (DaumXmlToken)this.getWhichToken();
		
		if(token == DaumXmlToken.TOTAL){
			this.getResult().setQueryCount(new Integer(s));
			return;
		}
		RetrievedBlogEntryImpl item = getItem();
		if ((token != null) && (item != null) ){
			switch(token){
				case COMMENT:{
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
		return DaumXmlToken.values();
	}

	@Override
	protected boolean isBlogEntryToken(XmlToken t) {
		// TODO Auto-generated method stub
		return (DaumXmlToken.ITEM.compareTo(
				(DaumXmlToken)t) == 0);
	}

}
