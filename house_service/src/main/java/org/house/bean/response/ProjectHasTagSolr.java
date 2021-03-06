package org.house.bean.response;

import org.house.db.entity.solr.ProjectTagSolr;

public class ProjectHasTagSolr {
	protected String projectId; // 项目ID
	protected String projectName; // 所属项目名称
	protected ProjectTagSolr projectTag;
	protected String projectAddress; // 项目地址

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

	public ProjectTagSolr getProjectTag() {
		return this.projectTag;
	}

	public void setProjectTag(final ProjectTagSolr projectTag) {
		this.projectTag = projectTag;
	}

	public String getProjectAddress() {
		return this.projectAddress;
	}

	public void setProjectAddress(final String projectAddress) {
		this.projectAddress = projectAddress;
	}

}