package org.house.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.house.bean.EarthBasicData;

import com.google.common.collect.Lists;

public class WebSpider {
	static CloseableHttpClient client = HttpClientBuilder.create().build();

	private static void setRequestHeader(final HttpRequestBase HttpRequestBase) {
		HttpRequestBase.setHeader(new BasicHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
		HttpRequestBase.setHeader(new BasicHeader("Accept-Encoding", "gzip, deflate"));
		HttpRequestBase.setHeader(new BasicHeader("Accept-Language", "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4"));
		HttpRequestBase.setHeader(new BasicHeader("Cache-Control", "max-age=0"));
		HttpRequestBase.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
		HttpRequestBase.setHeader(new BasicHeader("Host", "www.laho.gov.cn"));
		HttpRequestBase.setHeader(new BasicHeader("Origin", "http://www.laho.gov.cn"));
		HttpRequestBase
				.setHeader(new BasicHeader("Referer", "http://www.laho.gov.cn/g4cdata/search/laho/preSellSearch.jsp"));
		HttpRequestBase.setHeader(new BasicHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36"));
	}

	public static List<EarthBasicData> getPreSellEarthBasicData(final int page)
			throws ClientProtocolException, IOException {
		final HttpPost httpPost = new HttpPost("http://www.laho.gov.cn/g4cdata/search/laho/preSellSearch.jsp");
		setRequestHeader(httpPost);

		final List<NameValuePair> parameters = Lists.newArrayList();
		parameters.add(new BasicNameValuePair("orderfield", ""));
		parameters.add(new BasicNameValuePair("ordertype", ""));
		parameters.add(new BasicNameValuePair("presellNo", ""));
		parameters.add(new BasicNameValuePair("projectName", ""));
		parameters.add(new BasicNameValuePair("awardedStartDate", ""));
		parameters.add(new BasicNameValuePair("awardedEndDate", ""));
		parameters.add(new BasicNameValuePair("layoutNo", ""));
		parameters.add(new BasicNameValuePair("projectAddress", ""));
		parameters.add(new BasicNameValuePair("developer", ""));
		parameters.add(new BasicNameValuePair("imgvalue", ""));
		parameters.add(new BasicNameValuePair("chnlname", "预售证"));
		parameters.add(new BasicNameValuePair("currPage", "" + page));
		parameters.add(new BasicNameValuePair("judge", "1"));
		final UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, "gb2312");

		httpPost.setEntity(entity);

		final CloseableHttpResponse theResponse = client.execute(httpPost);

		final List<EarthBasicData> earthBasicDatas = Lists.<EarthBasicData> newArrayList();
		final String content = EntityUtils.toString(theResponse.getEntity());
		int idx = 0;
		final String tag = "box_tab_style02_td";
		final String tag2 = "target=\"_blank\">";
		final String tag3 = "</a></td>";
		final String tag4 = "<a href=\"";
		final String tag5 = "\" target=\"_blank\"";
		String projectId = null;
		while (true) {
			final int idx1 = content.indexOf(tag, idx);
			if (idx1 == -1) {
				break;
			}
			final int idx2 = content.indexOf("</td>", idx1);
			final int number = Integer.parseInt(content.substring(idx1 + tag.length() + 2, idx2));

			final int idx3 = content.indexOf(tag2, idx2);
			final int idx4 = content.indexOf(tag3, idx3);
			final String preSellLicenceNo = content.substring(idx3 + tag2.length(), idx4);

			final int idx5 = content.indexOf(tag2, idx4);
			final int idx6 = content.indexOf(tag3, idx5);
			final String projectName = content.substring(idx5 + tag2.length(), idx6);

			final int idx7 = content.indexOf(tag2, idx6);
			final int idx8 = content.indexOf(tag3, idx7);
			final String developer = content.substring(idx7 + tag2.length(), idx8);

			final int idx9 = content.indexOf(tag2, idx8);
			final int idx10 = content.indexOf(tag3, idx9);
			final int count = Integer.parseInt(content.substring(idx9 + tag2.length(), idx10));

			final int idx11 = content.indexOf(tag2, idx10);
			final int idx12 = content.indexOf(tag3, idx11);
			final int totalCount = Integer.parseInt(content.substring(idx11 + tag2.length(), idx12));

			final int idx13 = content.indexOf(tag2, idx12);
			final int idx14 = content.indexOf(tag3, idx13);
			final String totalArea = content.substring(idx13 + tag2.length(), idx14);

			final int idx15 = content.indexOf(tag2, idx14);
			final int idx16 = content.indexOf(tag3, idx15);
			final String licenceDate = content.substring(idx15 + tag2.length(), idx16);

			if (projectId == null) {
				final int idx17 = content.indexOf(tag4, idx2);
				final int idx18 = content.indexOf(tag5, idx17);
				projectId = content.substring(idx17 + tag4.length(), idx18).split("pjID=")[1].split("&")[0];
			}

			earthBasicDatas.add(new EarthBasicData(number, preSellLicenceNo, projectName, developer, count, totalCount,
					totalArea, licenceDate, projectId));

			idx = idx16 + tag3.length();
		}

		return earthBasicDatas;
	}

	public static List<EarthBasicData> getPreSellEarthDetailData(final String pjID)
			throws ClientProtocolException, IOException {
		final HttpGet httpGet = new HttpGet("http://www.laho.gov.cn/g4cdata/search/laho/project.jsp?pjID=" + pjID);
		setRequestHeader(httpGet);

		final CloseableHttpResponse theResponse = client.execute(httpGet);

		// TODO HERE

		final List<EarthBasicData> earthBasicDatas = Lists.<EarthBasicData> newArrayList();
		final String content = EntityUtils.toString(theResponse.getEntity());
		int idx = 0;
		final String tag = "box_tab_style02_td";
		final String tag2 = "target=\"_blank\">";
		final String tag3 = "</a></td>";
		final String tag4 = "<a href=\"";
		final String tag5 = "\" target=\"_blank\"";
		String projectId = null;
		while (true) {
			final int idx1 = content.indexOf(tag, idx);
			if (idx1 == -1) {
				break;
			}
			final int idx2 = content.indexOf("</td>", idx1);
			final int number = Integer.parseInt(content.substring(idx1 + tag.length() + 2, idx2));

			final int idx3 = content.indexOf(tag2, idx2);
			final int idx4 = content.indexOf(tag3, idx3);
			final String preSellLicenceNo = content.substring(idx3 + tag2.length(), idx4);

			final int idx5 = content.indexOf(tag2, idx4);
			final int idx6 = content.indexOf(tag3, idx5);
			final String projectName = content.substring(idx5 + tag2.length(), idx6);

			final int idx7 = content.indexOf(tag2, idx6);
			final int idx8 = content.indexOf(tag3, idx7);
			final String developer = content.substring(idx7 + tag2.length(), idx8);

			final int idx9 = content.indexOf(tag2, idx8);
			final int idx10 = content.indexOf(tag3, idx9);
			final int count = Integer.parseInt(content.substring(idx9 + tag2.length(), idx10));

			final int idx11 = content.indexOf(tag2, idx10);
			final int idx12 = content.indexOf(tag3, idx11);
			final int totalCount = Integer.parseInt(content.substring(idx11 + tag2.length(), idx12));

			final int idx13 = content.indexOf(tag2, idx12);
			final int idx14 = content.indexOf(tag3, idx13);
			final String totalArea = content.substring(idx13 + tag2.length(), idx14);

			final int idx15 = content.indexOf(tag2, idx14);
			final int idx16 = content.indexOf(tag3, idx15);
			final String licenceDate = content.substring(idx15 + tag2.length(), idx16);

			if (projectId == null) {
				final int idx17 = content.indexOf(tag4, idx2);
				final int idx18 = content.indexOf(tag5, idx17);
				projectId = content.substring(idx17 + tag4.length(), idx18).split("pjID=")[1].split("&")[0];
			}

			earthBasicDatas.add(new EarthBasicData(number, preSellLicenceNo, projectName, developer, count, totalCount,
					totalArea, licenceDate, projectId));

			idx = idx16 + tag3.length();
		}

		return earthBasicDatas;
	}

}
