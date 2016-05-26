package org.house.db.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.house.util.JsonHelper;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(name = "project_basic_data")
public class ProjectBasicData extends AbstractDbBean implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String projectId; // 项目ID
	protected String projectName; // 所属项目名称
	protected String preSellLicenceNo; // 预售许可证号
	protected String projectAddress; // 项目地址
	protected String developer; // 开发商
	protected String division; // 行政区划
	protected String totalCostArea; // 总占地面积
	protected String totalBuildArea; // 总建筑面积
	protected String qualificationLicenceNo; // 资质证书编号
	protected String usagee; // 用途
	protected int preSellTotalCount; // 批准预售总套数
	protected String preSellTotalArea; // 批准预售总面积
	protected int buildingCount; // 预售幢数
	protected String licenceDate; // 发证日期
	// 已售套数、已售面积 待另建表在本页抓取

	@Override
	public int hashCode() {
		return new StringBuilder().append(this.projectId).append(this.projectName).append(this.preSellLicenceNo)
				.append(this.projectAddress).append(this.developer).append(this.division).append(this.totalCostArea)
				.append(this.totalBuildArea).append(this.qualificationLicenceNo).append(this.usagee)
				.append(this.preSellTotalCount).append(this.preSellTotalArea).append(this.buildingCount)
				.append(this.licenceDate).toString().hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof ProjectBasicData) {
			final ProjectBasicData anObject = (ProjectBasicData) obj;
			if (ObjectUtils.nullSafeEquals(this.projectId, anObject.getProjectId())
					&& ObjectUtils.nullSafeEquals(this.projectName, anObject.getProjectName())
					&& ObjectUtils.nullSafeEquals(this.preSellLicenceNo, anObject.getPreSellLicenceNo())
					&& ObjectUtils.nullSafeEquals(this.projectAddress, anObject.getProjectAddress())
					&& ObjectUtils.nullSafeEquals(this.developer, anObject.getDeveloper())
					&& ObjectUtils.nullSafeEquals(this.division, anObject.getDivision())
					&& ObjectUtils.nullSafeEquals(this.totalCostArea, anObject.getTotalCostArea())
					&& ObjectUtils.nullSafeEquals(this.totalBuildArea, anObject.getTotalBuildArea())
					&& ObjectUtils.nullSafeEquals(this.qualificationLicenceNo, anObject.getQualificationLicenceNo())
					&& ObjectUtils.nullSafeEquals(this.usagee, anObject.getUsagee())
					&& ObjectUtils.nullSafeEquals(this.preSellTotalCount, anObject.getPreSellTotalCount())
					&& ObjectUtils.nullSafeEquals(this.preSellTotalArea, anObject.getPreSellTotalArea())
					&& ObjectUtils.nullSafeEquals(this.buildingCount, anObject.getBuildingCount())
					&& ObjectUtils.nullSafeEquals(this.licenceDate, anObject.getLicenceDate())) {
				return true;
			}
		}
		return false;
	}

	public void fromObj(final ProjectBasicData theObj) {
		this.projectId = theObj.projectId;
		this.projectName = theObj.projectName;
		this.preSellLicenceNo = theObj.preSellLicenceNo;
		this.projectAddress = theObj.projectAddress;
		this.developer = theObj.developer;
		this.division = theObj.division;
		this.totalCostArea = theObj.totalCostArea;
		this.totalBuildArea = theObj.totalBuildArea;
		this.qualificationLicenceNo = theObj.qualificationLicenceNo;
		this.usagee = theObj.usagee;
		this.preSellTotalCount = theObj.preSellTotalCount;
		this.preSellTotalArea = theObj.preSellTotalArea;
		this.buildingCount = theObj.buildingCount;
		this.licenceDate = theObj.licenceDate;
	}

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

	@Override
	public String toString() {
		return JsonHelper.gson.toJson(this);
	}
}