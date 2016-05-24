package org.house.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class EarthBasicData {
	private int number; // 编号，并没有用
	private String preSellLicenceNo; // 预售许可证号
	private String projectName; // 所属项目名称
	private String developer; // 开发商
	private int count; // 预售幢数
	private int totalCount; // 预售总套数
	private String totalArea; // 预售总面积
	private String licenceDate; // 发证日期
	private String projectId; // 项目ID

	public EarthBasicData() {

	}

	public EarthBasicData(final int number, final String preSellLicenceNo, final String projectName,
			final String developer, final int count, final int totalCount, final String totalArea,
			final String licenceDate, final String projectId) {
		this.number = number;
		this.preSellLicenceNo = preSellLicenceNo;
		this.projectName = projectName;
		this.developer = developer;
		this.count = count;
		this.totalCount = totalCount;
		this.totalArea = totalArea;
		this.licenceDate = licenceDate;
		this.projectId = projectId;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(final int number) {
		this.number = number;
	}

	public String getPreSellLicenceNo() {
		return this.preSellLicenceNo;
	}

	public void setPreSellLicenceNo(final String preSellLicenceNo) {
		this.preSellLicenceNo = preSellLicenceNo;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(final String projectName) {
		this.projectName = projectName;
	}

	public String getDeveloper() {
		return this.developer;
	}

	public void setDeveloper(final String developer) {
		this.developer = developer;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(final int count) {
		this.count = count;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(final int totalCount) {
		this.totalCount = totalCount;
	}

	public String getTotalArea() {
		return this.totalArea;
	}

	public void setTotalArea(final String totalArea) {
		this.totalArea = totalArea;
	}

	public String getLicenceDate() {
		return this.licenceDate;
	}

	public void setLicenceDate(final String licenceDate) {
		this.licenceDate = licenceDate;
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(final String projectId) {
		this.projectId = projectId;
	}
}
