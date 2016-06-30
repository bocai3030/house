package org.house.bean.response;

import org.house.db.entity.jpa.ProjectTagJpa;

public class ProjectHasTagJpa {
	protected String projectId; // 项目ID
	protected String projectName; // 所属项目名称
	protected ProjectTagJpa projectTag;
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

	public ProjectTagJpa getProjectTag() {
		return this.projectTag;
	}

	public void setProjectTag(final ProjectTagJpa projectTag) {
		this.projectTag = projectTag;
	}

	public String getProjectAddress() {
		return this.projectAddress;
	}

	public void setProjectAddress(final String projectAddress) {
		this.projectAddress = projectAddress;
	}

}