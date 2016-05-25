package org.house.db.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(name = "ad_name_lang_map_v4")
public class EarthBasicData extends AbstractDbBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String earthLicenceNo; // 国土证号
	private String location; // 土地座落
	private String user; // 土地使用者
	private String earthNo; // 地号
	private String graphNo;// 图号
	private String usage;// 土地用途
	private String level; // 土地等级
	private String borrowFrom; // 土地出让年限自
	private String useRightKind; // 使用权类型
	private String useArea; // 使用权面积
	private String shareArea; // 其中共用分推面积
	private String licenceIssueDate; // 发证日期

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

	public String getUser() {
		return this.user;
	}

	public void setUser(final String user) {
		this.user = user;
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

	public String getUsage() {
		return this.usage;
	}

	public void setUsage(final String usage) {
		this.usage = usage;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(final String level) {
		this.level = level;
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