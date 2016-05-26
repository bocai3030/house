package org.house.db.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(name = "earth_basic_data")
public class EarthBasicData extends AbstractDbBean implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String earthLicenceId; // 国土证号ID
	protected String earthLicenceNo; // 国土证号
	protected String location; // 土地座落
	protected String userr; // 土地使用者
	protected String earthNo; // 地号
	protected String graphNo;// 图号
	protected String usagee;// 土地用途
	protected String levell; // 土地等级
	protected String borrowFrom; // 土地出让年限自
	protected String useRightKind; // 使用权类型
	protected String useArea; // 使用权面积
	protected String shareArea; // 其中共用分推面积
	protected String licenceIssueDate; // 发证日期

	@Override
	public int hashCode() {
		return new StringBuilder().append(this.earthLicenceId)//
				.append(this.earthLicenceNo)//
				.append(this.location)//
				.append(this.userr)//
				.append(this.earthNo)//
				.append(this.graphNo)//
				.append(this.usagee)//
				.append(this.levell)//
				.append(this.borrowFrom)//
				.append(this.useRightKind)//
				.append(this.useArea)//
				.append(this.shareArea)//
				.append(this.licenceIssueDate).toString().hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof EarthBasicData) {
			final EarthBasicData anObject = (EarthBasicData) obj;
			if (ObjectUtils.nullSafeEquals(this.earthLicenceId, anObject.getEarthLicenceId())
					&& ObjectUtils.nullSafeEquals(this.earthLicenceNo, anObject.getEarthLicenceNo())
					&& ObjectUtils.nullSafeEquals(this.location, anObject.getLocation())
					&& ObjectUtils.nullSafeEquals(this.userr, anObject.getUserr())
					&& ObjectUtils.nullSafeEquals(this.earthNo, anObject.getEarthNo())
					&& ObjectUtils.nullSafeEquals(this.graphNo, anObject.getGraphNo())
					&& ObjectUtils.nullSafeEquals(this.usagee, anObject.getUsagee())
					&& ObjectUtils.nullSafeEquals(this.levell, anObject.getLevell())
					&& ObjectUtils.nullSafeEquals(this.borrowFrom, anObject.getBorrowFrom())
					&& ObjectUtils.nullSafeEquals(this.useRightKind, anObject.getUseRightKind())
					&& ObjectUtils.nullSafeEquals(this.useArea, anObject.getUseArea())
					&& ObjectUtils.nullSafeEquals(this.shareArea, anObject.getShareArea())
					&& ObjectUtils.nullSafeEquals(this.licenceIssueDate, anObject.getLicenceIssueDate())) {
				return true;
			}
		}
		return false;
	}

	public void fromObj(final EarthBasicData theObj) {
		// this.earthLicenceId = theObj.earthLicenceId; // NO ID
		this.earthLicenceNo = theObj.earthLicenceNo;
		this.location = theObj.location;
		this.userr = theObj.userr;
		this.earthNo = theObj.earthNo;
		this.graphNo = theObj.graphNo;
		this.usagee = theObj.usagee;
		this.levell = theObj.levell;
		this.borrowFrom = theObj.borrowFrom;
		this.useRightKind = theObj.useRightKind;
		this.useArea = theObj.useArea;
		this.shareArea = theObj.shareArea;
		this.licenceIssueDate = theObj.licenceIssueDate;
	}

	public String getEarthLicenceId() {
		return this.earthLicenceId;
	}

	public void setEarthLicenceId(final String earthLicenceId) {
		this.earthLicenceId = earthLicenceId;
	}

	public String getEarthLicenceNo() {
		return this.earthLicenceNo;
	}

	public void setEarthLicenceNo(final String earthLicenceNo) {
		this.earthLicenceNo = earthLicenceNo;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(final String location) {
		this.location = location;
	}

	public String getUserr() {
		return this.userr;
	}

	public void setUserr(final String userr) {
		this.userr = userr;
	}

	public String getEarthNo() {
		return this.earthNo;
	}

	public void setEarthNo(final String earthNo) {
		this.earthNo = earthNo;
	}

	public String getGraphNo() {
		return this.graphNo;
	}

	public void setGraphNo(final String graphNo) {
		this.graphNo = graphNo;
	}

	public String getUsagee() {
		return this.usagee;
	}

	public void setUsagee(final String usagee) {
		this.usagee = usagee;
	}

	public String getLevell() {
		return this.levell;
	}

	public void setLevell(final String levell) {
		this.levell = levell;
	}

	public String getBorrowFrom() {
		return this.borrowFrom;
	}

	public void setBorrowFrom(final String borrowFrom) {
		this.borrowFrom = borrowFrom;
	}

	public String getUseRightKind() {
		return this.useRightKind;
	}

	public void setUseRightKind(final String useRightKind) {
		this.useRightKind = useRightKind;
	}

	public String getUseArea() {
		return this.useArea;
	}

	public void setUseArea(final String useArea) {
		this.useArea = useArea;
	}

	public String getShareArea() {
		return this.shareArea;
	}

	public void setShareArea(final String shareArea) {
		this.shareArea = shareArea;
	}

	public String getLicenceIssueDate() {
		return this.licenceIssueDate;
	}

	public void setLicenceIssueDate(final String licenceIssueDate) {
		this.licenceIssueDate = licenceIssueDate;
	}
}