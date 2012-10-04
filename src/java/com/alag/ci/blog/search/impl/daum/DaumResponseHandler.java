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
 <description>선택하겠다만.) 사실 이 블로그 글도 이번 주말동안 제대로 된 학교공부를 하다가 중간에 쉬면서 쓰기 시작했다. <b>iPad</b>. 일단, 아이패드부터 시작하자. 아이패드에 대한 내 공식적 입장은 iAppBox 리뷰에서 확인할 수 있다. 지난...</description>
 <link>http://kudol.pe.kr/1484</link>
 <author>NHS Story: A New Beginning</author>
 <comment>http://kudol.pe.kr</comment>
 <pubDate>20100422104409</pubDate>
 </item>
<item>
 <title>22일(목) 오늘의 iPhone & <b>iPad</b> 최신 뉴스소식 모음</title>
 <description>iPhone & <b>iPad</b> 트위터로도 바로 리뷰를 보실수가 있습니다. 22일(목) 오늘의 iPhone & <b>iPad</b> 최신 뉴스소식 모음 AT&T 순익 월가 예상 웃돌아…아이폰 효과 AT&T는 21일(현지시간) 지난 1분기 순이익이 지난해 같은 시기에 비해 21% 줄...</description>
 <link>http://qswoo.tistory.com/905</link>
 <author>iPhone 과 사랑이 이루어지는 공간 초롱이37님 전문블로그</author>
 <comment>http://qswoo.tistory.com</comment>
 <pubDate>20100422094518</pubDate>
 </item>
<item>
 <title>21일(수) 오늘의 iPhone & <b>iPad</b> 최신 뉴스소식 모음</title>
 <description>iPhone & <b>iPad</b> 트위터로도 바로 리뷰를 보실수가 있습니다. 21일(수) 오늘의 iPhone & <b>iPad</b> 최신 뉴스소식 모음 `신비주의` 애플의 차세대 아이폰 유출 경위? 신제품을 출시하기 전 철저한 보안으로 유명한 애플의 차세대 아이폰으로...</description>
 <link>http://qswoo.tistory.com/900</link>
 <author>iPhone 과 사랑이 이루어지는 공간 초롱이37님 전문블로그</author>
 <comment>http://qswoo.tistory.com</comment>
 <pubDate>20100421093354</pubDate>
 </item>
<item>
 <title>아이패드녀, <b>iPad</b> DJ 등장</title>
 <description>'아이폰녀' 와 같은 파급력을 가질 수 있을지는 부정적이나(개인적 의견) 어쨌든 'GIZMODO' 에서는 그녀를 [<b>IPad</b> DJ] 로 소개했다. [<b>iPad</b> DJ]로 소개된 그녀의 이름은 ‘Rona Sobhany’. 그녀는 두 대의 아이패드를 Numark M3 2ch...</description>
 <link>http://www.soulog.com/19</link>
 <author>www.soulog.com</author>
 <comment>http://www.soulog.com</comment>
 <pubDate>20100422101308</pubDate>
 </item>
<item>
 <title><b>iPad</b>에 대해 알아 두고 싶은 몇가지 것들</title>
 <description>출시 전부터 화제가 되고 있는 애플의 신제품 <b>iPad</b>. 필자는 현재, 해외출장중이지만, 어느 정도 긴 시간 사용할 기회를 얻었다. 사용한 것은, Wi-Fi판으로 뒷모습이 아름답다. 애플의 사이트에 게재되고 있는 사진을 본 사람들은...</description>
 <link>http://bastof.tistory.com/648</link>
 <author>Bastof 의 세상이야기 - Be A Stranger to Fear!</author>
 <comment>http://bastof.tistory.com</comment>
 <pubDate>20100413141049</pubDate>
 </item>
<item>
 <title><b>iPad</b> 애플리케이션, 영화사들의 콘텐츠 직접 유통에는...</title>
 <description>Key Message - 미국의 영화 관련 업계들이 <b>iPad</b> 애플리케이션에 대한 시각차를 드러내고 있다. 헐리우드 영화사들의 경우, iTunes 같은 기존 유통채널과 별개로 굳이 또 하나의 판매 채널을 만들 이유가 없다는 입장이어서 <b>iPad</b> 전용...</description>
 <link>http://blog.naver.com/zvezda/130084657553</link>
 <author>FutureProof of Digital World</author>
 <comment>http://blog.naver.com/zvezda</comment>
 <pubDate>20100421010934</pubDate>
 </item>
<item>
 <title><b>iPad</b> vs. Kindle: Who's the better e-Reader?</title>
 <description>The <b>iPad</b> is indeed a useful, entertaining, engaging and disruptive product, but is it really a better reading device than the traditional e-Ink readers currently on the market? We put the <b>iPad</b>’s backlit IPS LCD...</description>
 <link>http://blogs.zdnet.com/perlow/?p=12719&utm_source=feedburner&utm_medium=feed&utm_campaign=Feed%3A+ZDNetBlogs+%28ZDNet+All+Blogs%29</link>
 <author/>
 <comment>http://blogs.zdnet.com</comment>
 <pubDate>20100421131538</pubDate>
 </item>
<item>
 <title>아이패드 키노트 사용하기 Keynote for <b>iPad</b></title>
 <description>애플 키노트란, 애플사에서 만든 발표용 소프트웨어입니다. MS Office에 포함된 PowerPoint와 같은 역할을 합니다. 파워포인트가 좀 복잡한 도형 등을 편하게 작성할 수 있는 장점이 있는 반면, 애플 키노트는 다양한/강력한...</description>
 <link>http://bahnsville.tistory.com/339</link>
 <author>BAHNsville</author>
 <comment>http://bahnsville.tistory.com</comment>
 <pubDate>20100415105939</pubDate>
 </item>
<item>
 <title>직접 만져본 애플 아이패드(<b>iPad</b>), 빠르고 편했다.</title>
 <description>지난 글에서 밝힌 대로 애플 사의 아이패드(<b>iPad</b>)를 잠시나마 만져볼 기회가 있었습니다. 아이폰으로 전세계적인 선풍을 불러일으킨 애플이 야심차게 내놓은 제품이죠. 아직 우리나라에 정식 출시되지는 않았지만 라츠에서의 전시를...</description>
 <link>http://lazion.com/2511925</link>
 <author>라지온 LAZION.com</author>
 <comment>http://lazion.com</comment>
 <pubDate>20100414072254</pubDate>
 </item>
<item>
 <title>[<b>iPad</b>] 아이패드의 궁금증을 풀어 보자, 아이패드vs...</title>
 <description>지난해 아이폰 출시이후 IT계에 새 바람이 일고 있습니다. 아이폰을 만든 애플에서 아이패드(<b>iPad</b>)를 내 놓았고, 아직 한국에서는 출시 전이라 아이폰에 이어 아이패드의 성공여부에관해 찬반여론이 많은 걸로 알고 있습니다. SONY ...</description>
 <link>http://www.yesbedesign.com/198</link>
 <author>예스비 디자인닷컴</author>
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
