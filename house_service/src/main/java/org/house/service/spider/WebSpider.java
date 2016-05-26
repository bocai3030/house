package org.house.service.spider;

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
import org.house.db.entity.EarthBasicData;
import org.house.db.entity.PreSellLicenseData;
import org.house.db.entity.ProjectBasicData;

import com.google.common.collect.Lists;

public class WebSpider {
	static CloseableHttpClient client = HttpClientBuilder.create().build();

	private static void setRequestHeader(final HttpRequestBase HttpRequestBase, final String referer) {
		HttpRequestBase.setHeader(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
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
		HttpRequestBase.setHeader(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
		HttpRequestBase.setHeader(new BasicHeader("Accept-Encoding", "gzip, deflate"));
		HttpRequestBase.setHeader(new BasicHeader("Accept-Language", "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4"));
		HttpRequestBase.setHeader(new BasicHeader("Cache-Control", "max-age=0"));
		HttpRequestBase.setHeader(new BasicHeader("Host", "www.laho.gov.cn"));
		HttpRequestBase.setHeader(new BasicHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36"));
	}

	public static List<ProjectBasicData> getProjectBasicData(final int page) throws ClientProtocolException, IOException {
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

		final List<ProjectBasicData> earthBasicDatas = Lists.<ProjectBasicData>newArrayList();
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
			final String preSellLicenseId = content.substring(idx3 + tag2.length(), idx4);

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
			final String licenseDate = content.substring(idx15 + tag2.length(), idx16);

			if (projectId == null) {
				final int idx17 = content.indexOf(tag4, idx2);
				final int idx18 = content.indexOf(tag5, idx17);
				projectId = content.substring(idx17 + tag4.length(), idx18).split("pjID=")[1].split("&")[0];
			}

			projectBasicData.setProjectId(projectId);
			projectBasicData.setPreSellLicenseId(preSellLicenseId);
			projectBasicData.setProjectName(projectName);
			projectBasicData.setDeveloper(developer);
			projectBasicData.setBuildingCount(buildingCount);
			projectBasicData.setPreSellTotalCount(preSellTotalCount);
			projectBasicData.setPreSellTotalArea(preSellTotalArea);
			projectBasicData.setLicenseDate(licenseDate);

			fullFillProjectDetailData(projectBasicData);
			fullFillProjectLicenceIdData(projectBasicData);

			earthBasicDatas.add(projectBasicData);

			idx = idx16 + tag3.length();
			break;
		}

		return earthBasicDatas;
	}

	private static void fullFillProjectLicenceIdData(final ProjectBasicData projectBasicData) throws ClientProtocolException, IOException {
		final HttpGet httpGet = new HttpGet(
				"http://www.laho.gov.cn/g4cdata/search/laho/project_detail.jsp?changeproInfoTag=0&changePreSellTag=1&preSell="
						+ projectBasicData.getPreSellLicenseId() + "&pjID=" + projectBasicData.getProjectId() + "&name=ysz");
		setAjaxRequestHeader(httpGet);

		final CloseableHttpResponse theResponse = client.execute(httpGet);

		final String content = EntityUtils.toString(theResponse.getEntity());

		final String tag0 = "&country_id=";
		final String tag1 = "&agreeId=";
		final String tag2 = "&layoutId=";
		final String tag3 = "/project/country.jsp?country_name=";
		final String tag4 = "/project/workAgree.jsp?agreeName=";
		final String tag5 = "/project/layoutAgree.jsp?layoutName=";
		final String tag6 = "developer.jsp?pjID=";
		final String tag7 = "\";";
		final String tag8 = "&pjID=";

		final int idx1 = content.indexOf(tag3);
		final int idx2 = content.indexOf(tag7, idx1 + tag3.length());
		final String[] countryStr = content.substring(idx1 + tag3.length(), idx2).split(tag0);
		projectBasicData.setCountryName(countryStr[0]);
		projectBasicData.setCountryId(countryStr[1]);

		final int idx3 = content.indexOf(tag4, idx2 + tag7.length());
		final int idx4 = content.indexOf(tag7, idx3 + tag4.length());
		final String[] agreeStr = content.substring(idx3 + tag4.length(), idx4).split(tag1);
		projectBasicData.setAgreeName(agreeStr[0]);
		projectBasicData.setAgreeId(agreeStr[1]);

		final int idx5 = content.indexOf(tag5, idx4 + tag7.length());
		final int idx6 = content.indexOf(tag8, idx5 + tag5.length());
		final String[] layoutStr = content.substring(idx5 + tag5.length(), idx6).split(tag2);
		projectBasicData.setLayoutName(layoutStr[0]);
		projectBasicData.setLayoutId(layoutStr[1]);

		final int idx7 = content.indexOf(tag6, idx6 + tag8.length());
		final int idx8 = content.indexOf(tag7, idx7 + tag6.length());
		final String[] str = content.substring(idx7 + tag6.length(), idx8).split("&");
		projectBasicData.setDeveloperId(str[1].split("=")[1]);
		projectBasicData.setSectionId(str[2].split("=")[1]);
	}

	private static void fullFillProjectDetailData(final ProjectBasicData projectBasicData) throws ClientProtocolException, IOException {
		final HttpGet httpGet = new HttpGet("http://www.laho.gov.cn/g4cdata/search/laho/project.jsp?pjID=" + projectBasicData.getProjectId());
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
		final String usagee = content.substring(idx13 + tag2.length() + 2, idx14);
		projectBasicData.setUsagee(usagee);
	}

	public static PreSellLicenseData getPreSellLicenseData(final String projectId, final String preSellLicenseId)
			throws ClientProtocolException, IOException {
		final HttpGet httpGet = new HttpGet("http://www.laho.gov.cn/g4cdata/search/project/preSell.jsp?pjID=" + projectId + "&presell="
				+ preSellLicenseId + "&maxPrice=&groundPrice=");
		setAjaxRequestHeader(httpGet);

		final CloseableHttpResponse theResponse = client.execute(httpGet);

		final String content = EntityUtils.toString(theResponse.getEntity());

		final PreSellLicenseData preSellLicenseData = new PreSellLicenseData();
		preSellLicenseData.setPreSellLicenseId(preSellLicenseId);

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
		preSellLicenseData.setBuildingCount(buildingCount);

		final int idx4 = content.indexOf(tag2, idx3 + tag3.length());
		final int idx5 = content.indexOf(tag3, idx4 + tag2.length());
		final String buildingHouse = content.substring(idx4 + tag2.length() + tag4.length() + 1, idx5); // 这里多了一个空格
		preSellLicenseData.setBuildingHouse(buildingHouse);

		final int idx6 = content.indexOf(tag2, idx5 + tag3.length());
		final int idx7 = content.indexOf(tag3, idx6 + tag2.length());
		final String builtHouse = content.substring(idx6 + tag2.length() + tag4.length(), idx7);
		preSellLicenseData.setBuiltHouse(builtHouse);

		final int idx8 = content.indexOf(tag2, idx7 + tag3.length());
		final int idx9 = content.indexOf(tag3, idx8 + tag2.length());
		final int currentPhase = Integer.parseInt(content.substring(idx8 + tag2.length() + tag4.length(), idx9));
		preSellLicenseData.setCurrentPhase(currentPhase);

		final int idx10 = content.indexOf(tag2, idx9 + tag3.length());
		final int idx11 = content.indexOf(tag3, idx10 + tag2.length());
		final String currentPhaseBuildingArea = content.substring(idx10 + tag2.length() + tag4.length(), idx11);
		preSellLicenseData.setCurrentPhaseBuildingArea(currentPhaseBuildingArea);

		final int idx12 = content.indexOf(tag2, idx11 + tag3.length());
		final int idx13 = content.indexOf(tag3, idx12 + tag2.length());
		final String areaUpGround = content.substring(idx12 + tag2.length() + tag4.length(), idx13);
		preSellLicenseData.setAreaUpGround(areaUpGround);

		final int idx14 = content.indexOf(tag2, idx13 + tag3.length());
		final int idx15 = content.indexOf(tag3, idx14 + tag2.length());
		final String areaUnderGround = content.substring(idx14 + tag2.length() + tag4.length(), idx15);
		preSellLicenseData.setAreaUnderGround(areaUnderGround);

		final int idx16 = content.indexOf(tag2, idx15 + tag3.length());
		final int idx17 = content.indexOf(tag3, idx16 + tag2.length());
		final int unitCount = Integer.parseInt(content.substring(idx16 + tag2.length() + tag4.length(), idx17));
		preSellLicenseData.setUnitCount(unitCount);

		final int idx18 = content.indexOf(tag2, idx17 + tag3.length());
		final int idx19 = content.indexOf(tag3, idx18 + tag2.length());
		final String totalBuidingArea = content.substring(idx18 + tag2.length() + tag4.length(), idx19);
		preSellLicenseData.setTotalBuidingArea(totalBuidingArea);

		final int idx20 = content.indexOf(tag2, idx19 + tag3.length());
		final int idx21 = content.indexOf(tag3, idx20 + tag2.length());
		final String contactPersion = content.substring(idx20 + tag2.length() + tag4.length(), idx21);
		preSellLicenseData.setContactPersion(contactPersion);

		final int idx22 = content.indexOf(tag2, idx21 + tag3.length());
		final int idx23 = content.indexOf(tag3, idx22 + tag2.length());
		final String mortgage = content.substring(idx22 + tag2.length() + tag4.length(), idx23);
		preSellLicenseData.setMortgage(mortgage);

		final int idx24 = content.indexOf(tag2, idx23 + tag3.length());
		final int idx25 = content.indexOf(tag3, idx24 + tag2.length());
		final String supportingArea = content.substring(idx24 + tag2.length() + tag4.length(), idx25);
		preSellLicenseData.setSupportingArea(supportingArea);

		final int idx26 = content.indexOf(tag5, idx25 + tag3.length());
		final int idx27 = content.indexOf(tag3, idx26 + tag5.length());
		final String validateFrom = content.substring(idx26 + tag5.length() + tag4.length() + 1, idx27);
		preSellLicenseData.setValidateFrom(validateFrom);

		final int idx28 = content.indexOf(tag5, idx27 + tag3.length());
		final int idx29 = content.indexOf(tag3, idx28 + tag5.length());
		final String validateTo = content.substring(idx28 + tag5.length() + tag4.length() + 1, idx29);
		preSellLicenseData.setValidateTo(validateTo);

		final int idx30 = content.indexOf(tag2, idx29 + tag3.length());
		final int idx31 = content.indexOf(tag3, idx30 + tag2.length());
		final String licenseIssueDate = content.substring(idx30 + tag2.length() + tag4.length(), idx31);
		preSellLicenseData.setLicenseIssueDate(licenseIssueDate);

		final int idx32 = content.indexOf(tag6, idx31 + tag3.length());

		final int idx33 = content.indexOf(tag2, idx32 + tag6.length());
		final int idx34 = content.indexOf(tag7, idx33 + tag2.length());
		final int distributeOfResidentialCount = Integer.parseInt(content.substring(idx33 + tag2.length() + 2, idx34));
		preSellLicenseData.setDistributeOfResidentialCount(distributeOfResidentialCount);

		final int idx35 = content.indexOf(tag2, idx34 + tag7.length());
		final int idx36 = content.indexOf(tag7, idx35 + tag2.length());
		final int distributeOfBussinessCount = Integer.parseInt(content.substring(idx35 + tag2.length() + 2, idx36));
		preSellLicenseData.setDistributeOfBussinessCount(distributeOfBussinessCount);

		final int idx37 = content.indexOf(tag2, idx36 + tag7.length());
		final int idx38 = content.indexOf(tag7, idx37 + tag2.length());
		final int distributeOfOfficeCount = Integer.parseInt(content.substring(idx37 + tag2.length() + 2, idx38));
		preSellLicenseData.setDistributeOfOfficeCount(distributeOfOfficeCount);

		final int idx39 = content.indexOf(tag2, idx38 + tag7.length());
		final int idx40 = content.indexOf(tag7, idx39 + tag2.length());
		final int distributeOfParkingCount = Integer.parseInt(content.substring(idx39 + tag2.length() + 2, idx40));
		preSellLicenseData.setDistributeOfParkingCount(distributeOfParkingCount);

		final int idx41 = content.indexOf(tag2, idx40 + tag7.length());
		final int idx42 = content.indexOf(tag7, idx41 + tag2.length());
		final int distributeOfOtherCount = Integer.parseInt(content.substring(idx41 + tag2.length() + 2, idx42));
		preSellLicenseData.setDistributeOfOtherCount(distributeOfOtherCount);

		final int idx43 = content.indexOf(tag8, idx42 + tag7.length());

		final int idx44 = content.indexOf(tag2, idx43 + tag8.length());
		final int idx45 = content.indexOf(tag7, idx44 + tag2.length());
		final String distributeOfResidentialArea = content.substring(idx44 + tag2.length() + 2, idx45);
		preSellLicenseData.setDistributeOfResidentialArea(distributeOfResidentialArea);

		final int idx46 = content.indexOf(tag2, idx45 + tag7.length());
		final int idx47 = content.indexOf(tag7, idx46 + tag2.length());
		final String distributeOfBussinessArea = content.substring(idx46 + tag2.length() + 2, idx47);
		preSellLicenseData.setDistributeOfBussinessArea(distributeOfBussinessArea);

		final int idx48 = content.indexOf(tag2, idx47 + tag7.length());
		final int idx49 = content.indexOf(tag7, idx48 + tag2.length());
		final String distributeOfOfficeArea = content.substring(idx48 + tag2.length() + 2, idx49);
		preSellLicenseData.setDistributeOfOfficeArea(distributeOfOfficeArea);

		final int idx50 = content.indexOf(tag2, idx49 + tag7.length());
		final int idx51 = content.indexOf(tag7, idx50 + tag2.length());
		final String distributeOfParkingArea = content.substring(idx50 + tag2.length() + 2, idx51);
		preSellLicenseData.setDistributeOfParkingArea(distributeOfParkingArea);

		final int idx52 = content.indexOf(tag2, idx51 + tag7.length());
		final int idx53 = content.indexOf(tag7, idx52 + tag2.length());
		final String distributeOfOtherArea = content.substring(idx52 + tag2.length() + 2, idx53);
		preSellLicenseData.setDistributeOfOtherArea(distributeOfOtherArea);
		return preSellLicenseData;
	}

	public static EarthBasicData getEarthBasicData(final String countryName, final String countryId, final String queryCountryId, String flag)
			throws ClientProtocolException, IOException {
		final HttpPost httpPost = new HttpPost("http://www.laho.gov.cn/g4cdata/search/project/country.jsp");
		setAjaxRequestHeader(httpPost);
		final List<NameValuePair> parameters = Lists.newArrayList();
		parameters.add(new BasicNameValuePair("country_name", countryName));
		parameters.add(new BasicNameValuePair("country_id", countryId));
		parameters.add(new BasicNameValuePair("countryId", queryCountryId));
		parameters.add(new BasicNameValuePair("flag", flag));
		final UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(parameters);
		httpPost.setEntity(urlEncodedFormEntity);

		final CloseableHttpResponse theResponse = client.execute(httpPost);

		final String content = EntityUtils.toString(theResponse.getEntity());

		final EarthBasicData earthBasicData = new EarthBasicData();
		earthBasicData.setEarthLicenseId(queryCountryId);

		final String tag1 = "国土证号";
		final String tag2 = "tab_style01_td\">";
		final String tag3 = "</td>";

		final int idx1 = content.indexOf(tag1);

		final int idx2 = content.indexOf(tag2, idx1 + tag1.length());
		final int idx3 = content.indexOf(tag3, idx2 + tag2.length());
		final String earthLicenseNo = content.substring(idx2 + tag2.length(), idx3);
		earthBasicData.setEarthLicenseNo(earthLicenseNo);

		final int idx4 = content.indexOf(tag2, idx3 + tag3.length());
		final int idx5 = content.indexOf(tag3, idx4 + tag2.length());
		final String location = content.substring(idx4 + tag2.length(), idx5);
		earthBasicData.setLocation(location);

		final int idx6 = content.indexOf(tag2, idx5 + tag3.length());
		final int idx7 = content.indexOf(tag3, idx6 + tag2.length());
		final String userr = content.substring(idx6 + tag2.length(), idx7);
		earthBasicData.setUserr(userr);

		final int idx8 = content.indexOf(tag2, idx7 + tag3.length());
		final int idx9 = content.indexOf(tag3, idx8 + tag2.length());
		final String earthNo = content.substring(idx8 + tag2.length(), idx9);
		earthBasicData.setEarthNo(earthNo);

		final int idx10 = content.indexOf(tag2, idx9 + tag3.length());
		final int idx11 = content.indexOf(tag3, idx10 + tag2.length());
		final String graphNo = content.substring(idx10 + tag2.length(), idx11);
		earthBasicData.setGraphNo(graphNo);

		final int idx12 = content.indexOf(tag2, idx11 + tag3.length());
		final int idx13 = content.indexOf(tag3, idx12 + tag2.length());
		final String usagee = content.substring(idx12 + tag2.length(), idx13);
		earthBasicData.setUsagee(usagee);

		final int idx14 = content.indexOf(tag2, idx13 + tag3.length());
		final int idx15 = content.indexOf(tag3, idx14 + tag2.length());
		final String levell = content.substring(idx14 + tag2.length(), idx15);
		earthBasicData.setLevell(levell);

		final int idx16 = content.indexOf(tag2, idx15 + tag3.length());
		final int idx17 = content.indexOf(tag3, idx16 + tag2.length());
		final String borrowFrom = content.substring(idx16 + tag2.length(), idx17);
		earthBasicData.setBorrowFrom(borrowFrom);

		final int idx18 = content.indexOf(tag2, idx17 + tag3.length());
		final int idx19 = content.indexOf(tag3, idx18 + tag2.length());
		final String useRightKind = content.substring(idx18 + tag2.length(), idx19);
		earthBasicData.setUseRightKind(useRightKind);

		final int idx20 = content.indexOf(tag2, idx19 + tag3.length());
		final int idx21 = content.indexOf(tag3, idx20 + tag2.length());
		final String useArea = content.substring(idx20 + tag2.length(), idx21);
		earthBasicData.setUseArea(useArea);

		final int idx22 = content.indexOf(tag2, idx21 + tag3.length());
		final int idx23 = content.indexOf(tag3, idx22 + tag2.length());
		final String shareArea = content.substring(idx22 + tag2.length(), idx23);
		earthBasicData.setShareArea(shareArea);

		final int idx24 = content.indexOf(tag2, idx23 + tag3.length());
		final int idx25 = content.indexOf(tag3, idx24 + tag2.length());
		final String licenseIssueDate = content.substring(idx24 + tag2.length(), idx25);
		earthBasicData.setLicenseIssueDate(licenseIssueDate);

		return earthBasicData;
	}
}
