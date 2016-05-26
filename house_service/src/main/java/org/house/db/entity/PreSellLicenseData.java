package org.house.db.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(name = "pre_sell_license_data")
public class PreSellLicenseData extends AbstractDbBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String preSellLicenseNo; // 预售许可证号
	private int buildingCount; // 预售幢数
	private String buildingHouse; // 报建屋数
	private String builtHouse; // 已建层数
	private int currentPhase;// 本期期数
	private String currentPhaseBuildingArea;// 本期报建总面积
	private String areaUpGround; // 地上面积
	private String areaUnderGround; // 地下面积
	private int unitCount; // 本期总单元套数
	private String totalBuidingArea; // 总建筑面积
	private String contactPersion; // 联系人
	private String mortgage; // 土地是否抵押
	private String supportingArea; // 配套面积
	private String validateFrom; // 有效期自
	private String validateTo; // 有效期至
	private String licenseIssueDate; // 发证日期
	private int distributeOfResidentialCount; // 住宅套数
	private String distributeOfResidentialArea; // 住宅面积
	private int distributeOfBussinessCount; // 商业套数
	private String distributeOfBussinessArea; // 商业面积
	private int distributeOfOfficeCount; // 办公套数
	private String distributeOfOfficeArea; // 办公面积
	private int distributeOfParkingCount; // 车位套数
	private String distributeOfParkingArea; // 车位面积
	private int distributeOfOtherCount; // 其他套数
	private String distributeOfOtherArea; // 其他面积

	public String getPreSellLicenseNo() {
		return this.preSellLicenseNo;
	}

	public void setPreSellLicenseNo(final String preSellLicenseNo) {
		this.preSellLicenseNo = preSellLicenseNo;
	}

	public int getBuildingCount() {
		return this.buildingCount;
	}

	public void setBuildingCount(final int buildingCount) {
		this.buildingCount = buildingCount;
	}

	public String getBuildingHouse() {
		return this.buildingHouse;
	}

	public void setBuildingHouse(final String buildingHouse) {
		this.buildingHouse = buildingHouse;
	}

	public String getBuiltHouse() {
		return this.builtHouse;
	}

	public void setBuiltHouse(final String builtHouse) {
		this.builtHouse = builtHouse;
	}

	public int getCurrentPhase() {
		return this.currentPhase;
	}

	public void setCurrentPhase(final int currentPhase) {
		this.currentPhase = currentPhase;
	}

	public String getCurrentPhaseBuildingArea() {
		return this.currentPhaseBuildingArea;
	}

	public void setCurrentPhaseBuildingArea(final String currentPhaseBuildingArea) {
		this.currentPhaseBuildingArea = currentPhaseBuildingArea;
	}

	public String getAreaUpGround() {
		return this.areaUpGround;
	}

	public void setAreaUpGround(final String areaUpGround) {
		this.areaUpGround = areaUpGround;
	}

	public String getAreaUnderGround() {
		return this.areaUnderGround;
	}

	public void setAreaUnderGround(final String areaUnderGround) {
		this.areaUnderGround = areaUnderGround;
	}

	public int getUnitCount() {
		return this.unitCount;
	}

	public void setUnitCount(final int unitCount) {
		this.unitCount = unitCount;
	}

	public String getTotalBuidingArea() {
		return this.totalBuidingArea;
	}

	public void setTotalBuidingArea(final String totalBuidingArea) {
		this.totalBuidingArea = totalBuidingArea;
	}

	public String getContactPersion() {
		return this.contactPersion;
	}

	public void setContactPersion(final String contactPersion) {
		this.contactPersion = contactPersion;
	}

	public String getMortgage() {
		return this.mortgage;
	}

	public void setMortgage(final String mortgage) {
		this.mortgage = mortgage;
	}

	public String getSupportingArea() {
		return this.supportingArea;
	}

	public void setSupportingArea(final String supportingArea) {
		this.supportingArea = supportingArea;
	}

	public String getValidateFrom() {
		return this.validateFrom;
	}

	public void setValidateFrom(final String validateFrom) {
		this.validateFrom = validateFrom;
	}

	public String getValidateTo() {
		return this.validateTo;
	}

	public void setValidateTo(final String validateTo) {
		this.validateTo = validateTo;
	}

	public String getLicenseIssueDate() {
		return this.licenseIssueDate;
	}

	public void setLicenseIssueDate(final String licenseIssueDate) {
		this.licenseIssueDate = licenseIssueDate;
	}

	public int getDistributeOfResidentialCount() {
		return this.distributeOfResidentialCount;
	}

	public void setDistributeOfResidentialCount(final int distributeOfResidentialCount) {
		this.distributeOfResidentialCount = distributeOfResidentialCount;
	}

	public String getDistributeOfResidentialArea() {
		return this.distributeOfResidentialArea;
	}

	public void setDistributeOfResidentialArea(final String distributeOfResidentialArea) {
		this.distributeOfResidentialArea = distributeOfResidentialArea;
	}

	public int getDistributeOfBussinessCount() {
		return this.distributeOfBussinessCount;
	}

	public void setDistributeOfBussinessCount(final int distributeOfBussinessCount) {
		this.distributeOfBussinessCount = distributeOfBussinessCount;
	}

	public String getDistributeOfBussinessArea() {
		return this.distributeOfBussinessArea;
	}

	public void setDistributeOfBussinessArea(final String distributeOfBussinessArea) {
		this.distributeOfBussinessArea = distributeOfBussinessArea;
	}

	public int getDistributeOfOfficeCount() {
		return this.distributeOfOfficeCount;
	}

	public void setDistributeOfOfficeCount(final int distributeOfOfficeCount) {
		this.distributeOfOfficeCount = distributeOfOfficeCount;
	}

	public String getDistributeOfOfficeArea() {
		return this.distributeOfOfficeArea;
	}

	public void setDistributeOfOfficeArea(final String distributeOfOfficeArea) {
		this.distributeOfOfficeArea = distributeOfOfficeArea;
	}

	public int getDistributeOfParkingCount() {
		return this.distributeOfParkingCount;
	}

	public void setDistributeOfParkingCount(final int distributeOfParkingCount) {
		this.distributeOfParkingCount = distributeOfParkingCount;
	}

	public String getDistributeOfParkingArea() {
		return this.distributeOfParkingArea;
	}

	public void setDistributeOfParkingArea(final String distributeOfParkingArea) {
		this.distributeOfParkingArea = distributeOfParkingArea;
	}

	public int getDistributeOfOtherCount() {
		return this.distributeOfOtherCount;
	}

	public void setDistributeOfOtherCount(final int distributeOfOtherCount) {
		this.distributeOfOtherCount = distributeOfOtherCount;
	}

	public String getDistributeOfOtherArea() {
		return this.distributeOfOtherArea;
	}

	public void setDistributeOfOtherArea(final String distributeOfOtherArea) {
		this.distributeOfOtherArea = distributeOfOtherArea;
	}
}