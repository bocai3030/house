package org.house.db.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(name = "project_basic_data")
public class ProjectBasicData extends AbstractDbBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// 还需要请求project_detail.jsp拿各种证件的id
	// @JsonProperty("test")
	private String projectId; // 项目ID
	private String projectName; // 所属项目名称
	private String preSellLicenceNo; // 预售许可证号
	private String projectAddress; // 项目地址
	private String developer; // 开发商
	private String division; // 行政区划
	private String totalCostArea; // 总占地面积
	private String totalBuildArea; // 总建筑面积
	private String qualificationLicenceNo; // 资质证书编号
	private String usagee; // 用途
	private int preSellTotalCount; // 批准预售总套数
	private String preSellTotalArea; // 批准预售总面积
	private int buildingCount; // 预售幢数
	private String licenceDate; // 发证日期
	// 已售套数、已售面积 待另建表在本页抓取

	public ProjectBasicData() {
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(final String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(final String projectName) {
		this.projectName = projectName;
	}

	public String getPreSellLicenceNo() {
		return this.preSellLicenceNo;
	}

	public void setPreSellLicenceNo(final String preSellLicenceNo) {
		this.preSellLicenceNo = preSellLicenceNo;
	}

	public String getProjectAddress() {
		return this.projectAddress;
	}

	public void setProjectAddress(final String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public String getDeveloper() {
		return this.developer;
	}

	public void setDeveloper(final String developer) {
		this.developer = developer;
	}

	public String getDivision() {
		return this.division;
	}

	public void setDivision(final String division) {
		this.division = division;
	}

	public String getTotalCostArea() {
		return this.totalCostArea;
	}

	public void setTotalCostArea(final String totalCostArea) {
		this.totalCostArea = totalCostArea;
	}

	public String getTotalBuildArea() {
		return this.totalBuildArea;
	}

	public void setTotalBuildArea(final String totalBuildArea) {
		this.totalBuildArea = totalBuildArea;
	}

	public String getQualificationLicenceNo() {
		return this.qualificationLicenceNo;
	}

	public void setQualificationLicenceNo(final String qualificationLicenceNo) {
		this.qualificationLicenceNo = qualificationLicenceNo;
	}

	public String getUsagee() {
		return this.usagee;
	}

	public void setUsagee(final String usage) {
		this.usagee = usage;
	}

	public int getPreSellTotalCount() {
		return this.preSellTotalCount;
	}

	public void setPreSellTotalCount(final int preSellTotalCount) {
		this.preSellTotalCount = preSellTotalCount;
	}

	public String getPreSellTotalArea() {
		return this.preSellTotalArea;
	}

	public void setPreSellTotalArea(final String preSellTotalArea) {
		this.preSellTotalArea = preSellTotalArea;
	}

	public int getBuildingCount() {
		return this.buildingCount;
	}

	public void setBuildingCount(final int buildingCount) {
		this.buildingCount = buildingCount;
	}

	public String getLicenceDate() {
		return this.licenceDate;
	}

	public void setLicenceDate(final String licenceDate) {
		this.licenceDate = licenceDate;
	}
}