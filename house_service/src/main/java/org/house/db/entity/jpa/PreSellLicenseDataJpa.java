package org.house.db.entity.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.house.util.C;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

@JsonAutoDetect
@Entity
@Table(name = "pre_sell_license_data")
public class PreSellLicenseDataJpa extends AbstractJpaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String preSellLicenseId; // 预售许可证号
	protected int buildingCount; // 预售幢数
	protected String buildingHouse; // 报建屋数
	protected String builtHouse; // 已建层数
	protected int currentPhase;// 本期期数
	protected String currentPhaseBuildingArea;// 本期报建总面积
	protected String areaUpGround; // 地上面积
	protected String areaUnderGround; // 地下面积
	protected int unitCount; // 本期总单元套数
	protected String totalBuidingArea; // 总建筑面积
	protected String contactPersion; // 联系人
	protected String mortgage; // 土地是否抵押
	protected String supportingArea; // 配套面积
	protected String validateFrom; // 有效期自
	protected String validateTo; // 有效期至
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = DateSerializer.class)
	@JsonFormat(pattern = C.DAY_JSON_FORMAT_PATTERN, timezone = C.DATE_JSON_FORMAT_TIMEZONE)
	protected Date licenseIssueDate; // 发证日期
	protected int distributeOfResidentialCount; // 住宅套数
	protected String distributeOfResidentialArea; // 住宅面积
	protected int distributeOfBussinessCount; // 商业套数
	protected String distributeOfBussinessArea; // 商业面积
	protected int distributeOfOfficeCount; // 办公套数
	protected String distributeOfOfficeArea; // 办公面积
	protected int distributeOfParkingCount; // 车位套数
	protected String distributeOfParkingArea; // 车位面积
	protected int distributeOfOtherCount; // 其他套数
	protected String distributeOfOtherArea; // 其他面积

	@Override
	public int hashCode() {
		int result = 17;
		if (this.preSellLicenseId != null) {
			result = (31 * result) + this.preSellLicenseId.hashCode();
		}
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof PreSellLicenseDataJpa) {
			final PreSellLicenseDataJpa anObject = (PreSellLicenseDataJpa) obj;
			if (ObjectUtils.nullSafeEquals(this.preSellLicenseId, anObject.getPreSellLicenseId())
					&& ObjectUtils.nullSafeEquals(this.buildingCount, anObject.getBuildingCount())
					&& ObjectUtils.nullSafeEquals(this.buildingHouse, anObject.getBuildingHouse())
					&& ObjectUtils.nullSafeEquals(this.builtHouse, anObject.getBuiltHouse())
					&& ObjectUtils.nullSafeEquals(this.currentPhase, anObject.getCurrentPhase())
					&& ObjectUtils.nullSafeEquals(this.currentPhaseBuildingArea, anObject.getCurrentPhaseBuildingArea())
					&& ObjectUtils.nullSafeEquals(this.areaUpGround, anObject.getAreaUpGround())
					&& ObjectUtils.nullSafeEquals(this.areaUnderGround, anObject.getAreaUnderGround())
					&& ObjectUtils.nullSafeEquals(this.unitCount, anObject.getUnitCount())
					&& ObjectUtils.nullSafeEquals(this.totalBuidingArea, anObject.getTotalBuidingArea())
					&& ObjectUtils.nullSafeEquals(this.contactPersion, anObject.getContactPersion())
					&& ObjectUtils.nullSafeEquals(this.mortgage, anObject.getMortgage())
					&& ObjectUtils.nullSafeEquals(this.supportingArea, anObject.getSupportingArea())
					&& ObjectUtils.nullSafeEquals(this.validateFrom, anObject.getValidateFrom())
					&& ObjectUtils.nullSafeEquals(this.validateTo, anObject.getValidateTo())
					&& ObjectUtils.nullSafeEquals(this.licenseIssueDate, anObject.getLicenseIssueDate())
					&& ObjectUtils.nullSafeEquals(this.distributeOfResidentialCount, anObject.getDistributeOfResidentialCount())
					&& ObjectUtils.nullSafeEquals(this.distributeOfResidentialArea, anObject.getDistributeOfResidentialArea())
					&& ObjectUtils.nullSafeEquals(this.distributeOfBussinessCount, anObject.getDistributeOfBussinessCount())
					&& ObjectUtils.nullSafeEquals(this.distributeOfBussinessArea, anObject.getDistributeOfBussinessArea())
					&& ObjectUtils.nullSafeEquals(this.distributeOfOfficeCount, anObject.getDistributeOfOfficeCount())
					&& ObjectUtils.nullSafeEquals(this.distributeOfOfficeArea, anObject.getDistributeOfOfficeArea())
					&& ObjectUtils.nullSafeEquals(this.distributeOfParkingCount, anObject.getDistributeOfParkingCount())
					&& ObjectUtils.nullSafeEquals(this.distributeOfParkingArea, anObject.getDistributeOfParkingArea())
					&& ObjectUtils.nullSafeEquals(this.distributeOfOtherCount, anObject.getDistributeOfOtherCount())
					&& ObjectUtils.nullSafeEquals(this.distributeOfOtherArea, anObject.getDistributeOfOtherArea())) {
				return true;
			}
		}
		return false;
	}

	public void fromObj(final PreSellLicenseDataJpa theObj) {
		// this.preSellLicenseNo = theObj.preSellLicenseNo; // NO ID
		this.buildingCount = theObj.buildingCount;
		this.buildingHouse = theObj.buildingHouse;
		this.builtHouse = theObj.builtHouse;
		this.currentPhase = theObj.currentPhase;
		this.currentPhaseBuildingArea = theObj.currentPhaseBuildingArea;
		this.areaUpGround = theObj.areaUpGround;
		this.areaUnderGround = theObj.areaUnderGround;
		this.unitCount = theObj.unitCount;
		this.totalBuidingArea = theObj.totalBuidingArea;
		this.contactPersion = theObj.contactPersion;
		this.mortgage = theObj.mortgage;
		this.supportingArea = theObj.supportingArea;
		this.validateFrom = theObj.validateFrom;
		this.validateTo = theObj.validateTo;
		this.licenseIssueDate = theObj.licenseIssueDate;
		this.distributeOfResidentialCount = theObj.distributeOfResidentialCount;
		this.distributeOfResidentialArea = theObj.distributeOfResidentialArea;
		this.distributeOfBussinessCount = theObj.distributeOfBussinessCount;
		this.distributeOfBussinessArea = theObj.distributeOfBussinessArea;
		this.distributeOfOfficeCount = theObj.distributeOfOfficeCount;
		this.distributeOfOfficeArea = theObj.distributeOfOfficeArea;
		this.distributeOfParkingCount = theObj.distributeOfParkingCount;
		this.distributeOfParkingArea = theObj.distributeOfParkingArea;
		this.distributeOfOtherCount = theObj.distributeOfOtherCount;
		this.distributeOfOtherArea = theObj.distributeOfOtherArea;
	}

	public String getPreSellLicenseId() {
		return this.preSellLicenseId;
	}

	public void setPreSellLicenseId(final String preSellLicenseId) {
		this.preSellLicenseId = preSellLicenseId;
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

	public Date getLicenseIssueDate() {
		return this.licenseIssueDate;
	}

	public void setLicenseIssueDate(final Date licenseIssueDate) {
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