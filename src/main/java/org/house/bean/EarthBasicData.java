package org.house.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class EarthBasicData {
	private int number;
	private String preSellLicenceNo;
	private String projectName;
	private String developer;
	private int count;
	private int totalCount;
	private String totalArea;
	private String licenceDate;

	public EarthBasicData() {

	}

	public EarthBasicData(int number, String preSellLicenceNo, String projectName, String developer, int count,
			int totalCount, String totalArea, String licenceDate) {
		this.number = number;
		this.preSellLicenceNo = preSellLicenceNo;
		this.projectName = projectName;
		this.developer = developer;
		this.count = count;
		this.totalCount = totalCount;
		this.totalArea = totalArea;
		this.licenceDate = licenceDate;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPreSellLicenceNo() {
		return this.preSellLicenceNo;
	}

	public void setPreSellLicenceNo(String preSellLicenceNo) {
		this.preSellLicenceNo = preSellLicenceNo;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDeveloper() {
		return this.developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getTotalArea() {
		return this.totalArea;
	}

	public void setTotalArea(String totalArea) {
		this.totalArea = totalArea;
	}

	public String getLicenceDate() {
		return this.licenceDate;
	}

	public void setLicenceDate(String licenceDate) {
		this.licenceDate = licenceDate;
	}

}
