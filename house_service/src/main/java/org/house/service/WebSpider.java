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
import org.house.bean.PreSellLicenceData;
import org.house.bean.ProjectBasicData;

import com.google.common.collect.Lists;

public class WebSpider {
	static CloseableHttpClient client = HttpClientBuilder.create().build();

	private static void setRequestHeader(final HttpRequestBase HttpRequestBase, final String referer) {
		HttpRequestBase.setHeader(new BasicHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
		HttpRequestBase.setHeader(new BasicHeader("Accept-Encoding", "gzip, deflate"));
		HttpRequestBase.setHeader(new BasicHeader("Accept-Language", "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4"));
		HttpRequestBase.setHeader(new BasicHeader("Cache-Control", "max-age=0"));
		HttpRequestBase.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
		HttpRequestBase.setHeader(new BasicHeader("Host", "www.laho.gov.cn"));
		HttpRequestBase.setHeader(new BasicHeader("Origin", "http://www.laho.gov.cn"));
		HttpRequestBase.setHeader(new BasicHeader("Referer", referer));
		HttpRequestBase.setHeader(new BasicHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36"));
	}

	private static void setAjaxRequestHeader(final HttpRequestBase HttpRequestBase) {
		HttpRequestBase.setHeader(new BasicHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
		HttpRequestBase.setHeader(new BasicHeader("Accept-Encoding", "gzip, deflate"));
		HttpRequestBase.setHeader(new BasicHeader("Accept-Language", "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4"));
		HttpRequestBase.setHeader(new BasicHeader("Cache-Control", "max-age=0"));
		HttpRequestBase.setHeader(new BasicHeader("Host", "www.laho.gov.cn"));
		HttpRequestBase.setHeader(new BasicHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36"));
	}

	public static List<ProjectBasicData> getProjectBasicData(final int page)
			throws ClientProtocolException, IOException {
		final HttpPost httpPost = new HttpPost("http://www.laho.gov.cn/g4cdata/search/laho/preSellSearch.jsp");
		setRequestHeader(httpPost, "http://www.laho.gov.cn/g4cdata/search/laho/preSellSearch.jsp");

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

		final List<ProjectBasicData> earthBasicDatas = Lists.<ProjectBasicData> newArrayList();
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

			final ProjectBasicData projectBasicData = new ProjectBasicData();

			final int idx2 = content.indexOf("</td>", idx1);
			Integer.parseInt(content.substring(idx1 + tag.length() + 2, idx2));

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
			final int buildingCount = Integer.parseInt(content.substring(idx9 + tag2.length(), idx10));

			final int idx11 = content.indexOf(tag2, idx10);
			final int idx12 = content.indexOf(tag3, idx11);
			final int preSellTotalCount = Integer.parseInt(content.substring(idx11 + tag2.length(), idx12));

			final int idx13 = content.indexOf(tag2, idx12);
			final int idx14 = content.indexOf(tag3, idx13);
			final String preSellTotalArea = content.substring(idx13 + tag2.length(), idx14);

			final int idx15 = content.indexOf(tag2, idx14);
			final int idx16 = content.indexOf(tag3, idx15);
			final String licenceDate = content.substring(idx15 + tag2.length(), idx16);

			if (projectId == null) {
				final int idx17 = content.indexOf(tag4, idx2);
				final int idx18 = content.indexOf(tag5, idx17);
				projectId = content.substring(idx17 + tag4.length(), idx18).split("pjID=")[1].split("&")[0];
			}

			projectBasicData.setProjectId(projectId);
			projectBasicData.setPreSellLicenceNo(preSellLicenceNo);
			projectBasicData.setProjectName(projectName);
			projectBasicData.setDeveloper(developer);
			projectBasicData.setBuildingCount(buildingCount);
			projectBasicData.setPreSellTotalCount(preSellTotalCount);
			projectBasicData.setPreSellTotalArea(preSellTotalArea);
			projectBasicData.setLicenceDate(licenceDate);

			fullFillProjectDetailData(projectBasicData);

			earthBasicDatas.add(projectBasicData);

			idx = idx16 + tag3.length();
		}

		return earthBasicDatas;
	}

	private static void fullFillProjectDetailData(final ProjectBasicData projectBasicData)
			throws ClientProtocolException, IOException {
		final HttpGet httpGet = new HttpGet(
				"http://www.laho.gov.cn/g4cdata/search/laho/project.jsp?pjID=" + projectBasicData.getProjectId());
		setAjaxRequestHeader(httpGet);

		final CloseableHttpResponse theResponse = client.execute(httpGet);

		final String content = EntityUtils.toString(theResponse.getEntity());

		final String tag1 = "<td class=\"tab_style01_th\">项目地址：</td>";
		final String tag2 = "tab_style01_td";
		final String tag3 = "</td>";
		final int idx1 = content.indexOf(tag1);

		final int idx2 = content.indexOf(tag2, idx1 + tag1.length());
		final int idx3 = content.indexOf(tag3, idx2 + tag2.length());
		final String projectAddress = content.substring(idx2 + tag2.length() + 2, idx3);
		projectBasicData.setProjectAddress(projectAddress);

		final int idx4 = content.indexOf(tag2, idx3); // 开发商

		final int idx5 = content.indexOf(tag2, idx4 + tag2.length());// 行政区划
		final int idx6 = content.indexOf(tag3, idx5 + tag2.length());
		final String division = content.substring(idx5 + tag2.length() + 2, idx6);
		projectBasicData.setDivision(division);

		final int idx7 = content.indexOf(tag2, idx6 + tag3.length());// 占地面积
		final int idx8 = content.indexOf(tag3, idx7 + tag2.length());
		final String totalCostArea = content.substring(idx7 + tag2.length() + 2, idx8);
		projectBasicData.setTotalCostArea(totalCostArea);

		final int idx9 = content.indexOf(tag2, idx8 + tag3.length());// 总建筑面积
		final int idx10 = content.indexOf(tag3, idx9 + tag2.length());
		final String totalBuildArea = content.substring(idx9 + tag2.length() + 2, idx10);
		projectBasicData.setTotalBuildArea(totalBuildArea);

		final int idx11 = content.indexOf(tag2, idx10 + tag3.length());// 资质证书编号
		final int idx12 = content.indexOf(tag3, idx11 + tag2.length());
		final String qualificationLicenceNo = content.substring(idx11 + tag2.length() + 2, idx12);
		projectBasicData.setQualificationLicenceNo(qualificationLicenceNo);

		final int idx13 = content.indexOf(tag2, idx12 + tag3.length());// 用途性质
		final int idx14 = content.indexOf(tag3, idx13 + tag2.length());
		final String usage = content.substring(idx13 + tag2.length() + 2, idx14);
		projectBasicData.setUsage(usage);
	}

	public static PreSellLicenceData getPreSellLicenceData(final String projectId, final String licenceId)
			throws ClientProtocolException, IOException {
		final HttpGet httpGet = new HttpGet("http://www.laho.gov.cn/g4cdata/search/project/preSell.jsp?pjID="
				+ projectId + "&presell=" + licenceId + "&maxPrice=&groundPrice=");
		setAjaxRequestHeader(httpGet);

		final CloseableHttpResponse theResponse = client.execute(httpGet);

		final String content = EntityUtils.toString(theResponse.getEntity());

		final PreSellLicenceData preSellLicenceData = new PreSellLicenceData();
		preSellLicenceData.setPreSellLicenceNo(licenceId);

		final String tag1 = "预售幢数";
		final String tag2 = "tab_style01_td";
		final String tag3 = "</p></td>";
		final String tag4 = "\"><p align=\"left\">";
		final String tag5 = "box_tab_style02_td";
		final String tag6 = "套数";
		final String tag7 = "</td>";
		final String tag8 = "面积";

		final int idx1 = content.indexOf(tag1);

		final int idx2 = content.indexOf(tag2, idx1 + tag1.length());
		final int idx3 = content.indexOf(tag3, idx2 + tag2.length());
		final int buildingCount = Integer.parseInt(content.substring(idx2 + tag2.length() + tag4.length(), idx3));
		preSellLicenceData.setBuildingCount(buildingCount);

		final int idx4 = content.indexOf(tag2, idx3 + tag3.length());
		final int idx5 = content.indexOf(tag3, idx4 + tag2.length());
		final String buildingHouse = content.substring(idx4 + tag2.length() + tag4.length() + 1, idx5); // 这里多了一个空格
		preSellLicenceData.setBuildingHouse(buildingHouse);

		final int idx6 = content.indexOf(tag2, idx5 + tag3.length());
		final int idx7 = content.indexOf(tag3, idx6 + tag2.length());
		final String builtHouse = content.substring(idx6 + tag2.length() + tag4.length(), idx7);
		preSellLicenceData.setBuiltHouse(builtHouse);

		final int idx8 = content.indexOf(tag2, idx7 + tag3.length());
		final int idx9 = content.indexOf(tag3, idx8 + tag2.length());
		final int currentPhase = Integer.parseInt(content.substring(idx8 + tag2.length() + tag4.length(), idx9));
		preSellLicenceData.setCurrentPhase(currentPhase);

		final int idx10 = content.indexOf(tag2, idx9 + tag3.length());
		final int idx11 = content.indexOf(tag3, idx10 + tag2.length());
		final String currentPhaseBuildingArea = content.substring(idx10 + tag2.length() + tag4.length(), idx11);
		preSellLicenceData.setCurrentPhaseBuildingArea(currentPhaseBuildingArea);

		final int idx12 = content.indexOf(tag2, idx11 + tag3.length());
		final int idx13 = content.indexOf(tag3, idx12 + tag2.length());
		final String areaUpGround = content.substring(idx12 + tag2.length() + tag4.length(), idx13);
		preSellLicenceData.setAreaUpGround(areaUpGround);

		final int idx14 = content.indexOf(tag2, idx13 + tag3.length());
		final int idx15 = content.indexOf(tag3, idx14 + tag2.length());
		final String areaUnderGround = content.substring(idx14 + tag2.length() + tag4.length(), idx15);
		preSellLicenceData.setAreaUnderGround(areaUnderGround);

		final int idx16 = content.indexOf(tag2, idx15 + tag3.length());
		final int idx17 = content.indexOf(tag3, idx16 + tag2.length());
		final int unitCount = Integer.parseInt(content.substring(idx16 + tag2.length() + tag4.length(), idx17));
		preSellLicenceData.setUnitCount(unitCount);

		final int idx18 = content.indexOf(tag2, idx17 + tag3.length());
		final int idx19 = content.indexOf(tag3, idx18 + tag2.length());
		final String totalBuidingArea = content.substring(idx18 + tag2.length() + tag4.length(), idx19);
		preSellLicenceData.setTotalBuidingArea(totalBuidingArea);

		final int idx20 = content.indexOf(tag2, idx19 + tag3.length());
		final int idx21 = content.indexOf(tag3, idx20 + tag2.length());
		final String contactPersion = content.substring(idx20 + tag2.length() + tag4.length(), idx21);
		preSellLicenceData.setContactPersion(contactPersion);

		final int idx22 = content.indexOf(tag2, idx21 + tag3.length());
		final int idx23 = content.indexOf(tag3, idx22 + tag2.length());
		final String mortgage = content.substring(idx22 + tag2.length() + tag4.length(), idx23);
		preSellLicenceData.setMortgage(mortgage);

		final int idx24 = content.indexOf(tag2, idx23 + tag3.length());
		final int idx25 = content.indexOf(tag3, idx24 + tag2.length());
		final String supportingArea = content.substring(idx24 + tag2.length() + tag4.length(), idx25);
		preSellLicenceData.setSupportingArea(supportingArea);

		final int idx26 = content.indexOf(tag5, idx25 + tag3.length());
		final int idx27 = content.indexOf(tag3, idx26 + tag5.length());
		final String validateFrom = content.substring(idx26 + tag5.length() + tag4.length() + 1, idx27);
		preSellLicenceData.setValidateFrom(validateFrom);

		final int idx28 = content.indexOf(tag5, idx27 + tag3.length());
		final int idx29 = content.indexOf(tag3, idx28 + tag5.length());
		final String validateTo = content.substring(idx28 + tag5.length() + tag4.length() + 1, idx29);
		preSellLicenceData.setValidateTo(validateTo);

		final int idx30 = content.indexOf(tag2, idx29 + tag3.length());
		final int idx31 = content.indexOf(tag3, idx30 + tag2.length());
		final String licenceIssueDate = content.substring(idx30 + tag2.length() + tag4.length(), idx31);
		preSellLicenceData.setLicenceIssueDate(licenceIssueDate);

		final int idx32 = content.indexOf(tag6, idx31 + tag3.length());

		final int idx33 = content.indexOf(tag2, idx32 + tag6.length());
		final int idx34 = content.indexOf(tag7, idx33 + tag2.length());
		final int distributeOfResidentialCount = Integer.parseInt(content.substring(idx33 + tag2.length() + 2, idx34));
		preSellLicenceData.setDistributeOfResidentialCount(distributeOfResidentialCount);

		final int idx35 = content.indexOf(tag2, idx34 + tag7.length());
		final int idx36 = content.indexOf(tag7, idx35 + tag2.length());
		final int distributeOfBussinessCount = Integer.parseInt(content.substring(idx35 + tag2.length() + 2, idx36));
		preSellLicenceData.setDistributeOfBussinessCount(distributeOfBussinessCount);

		final int idx37 = content.indexOf(tag2, idx36 + tag7.length());
		final int idx38 = content.indexOf(tag7, idx37 + tag2.length());
		final int distributeOfOfficeCount = Integer.parseInt(content.substring(idx37 + tag2.length() + 2, idx38));
		preSellLicenceData.setDistributeOfOfficeCount(distributeOfOfficeCount);

		final int idx39 = content.indexOf(tag2, idx38 + tag7.length());
		final int idx40 = content.indexOf(tag7, idx39 + tag2.length());
		final int distributeOfParkingCount = Integer.parseInt(content.substring(idx39 + tag2.length() + 2, idx40));
		preSellLicenceData.setDistributeOfParkingCount(distributeOfParkingCount);

		final int idx41 = content.indexOf(tag2, idx40 + tag7.length());
		final int idx42 = content.indexOf(tag7, idx41 + tag2.length());
		final int distributeOfOtherCount = Integer.parseInt(content.substring(idx41 + tag2.length() + 2, idx42));
		preSellLicenceData.setDistributeOfOtherCount(distributeOfOtherCount);

		final int idx43 = content.indexOf(tag8, idx42 + tag7.length());

		final int idx44 = content.indexOf(tag2, idx43 + tag8.length());
		final int idx45 = content.indexOf(tag7, idx44 + tag2.length());
		final String distributeOfResidentialArea = content.substring(idx44 + tag2.length() + 2, idx45);
		preSellLicenceData.setDistributeOfResidentialArea(distributeOfResidentialArea);

		final int idx46 = content.indexOf(tag2, idx45 + tag7.length());
		final int idx47 = content.indexOf(tag7, idx46 + tag2.length());
		final String distributeOfBussinessArea = content.substring(idx46 + tag2.length() + 2, idx47);
		preSellLicenceData.setDistributeOfBussinessArea(distributeOfBussinessArea);

		final int idx48 = content.indexOf(tag2, idx47 + tag7.length());
		final int idx49 = content.indexOf(tag7, idx48 + tag2.length());
		final String distributeOfOfficeArea = content.substring(idx48 + tag2.length() + 2, idx49);
		preSellLicenceData.setDistributeOfOfficeArea(distributeOfOfficeArea);

		final int idx50 = content.indexOf(tag2, idx49 + tag7.length());
		final int idx51 = content.indexOf(tag7, idx50 + tag2.length());
		final String distributeOfParkingArea = content.substring(idx50 + tag2.length() + 2, idx51);
		preSellLicenceData.setDistributeOfParkingArea(distributeOfParkingArea);

		final int idx52 = content.indexOf(tag2, idx51 + tag7.length());
		final int idx53 = content.indexOf(tag7, idx52 + tag2.length());
		final String distributeOfOtherArea = content.substring(idx52 + tag2.length() + 2, idx53);
		preSellLicenceData.setDistributeOfOtherArea(distributeOfOtherArea);
		return preSellLicenceData;
	}

	public static EarthBasicData getEarthBasicData(final String projectId, final String licenceId)
			throws ClientProtocolException, IOException {
		return null;
	}
}
