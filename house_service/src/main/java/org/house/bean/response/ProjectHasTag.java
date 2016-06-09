package org.house.bean.response;

import org.house.db.entity.ProjectTag;

public class ProjectHasTag {
	protected String projectId; // 项目ID
	protected String projectName; // 所属项目名称
	protected ProjectTag projectTag;

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

	public ProjectTag getProjectTag() {
		return this.projectTag;
	}

	public void setProjectTag(final ProjectTag projectTag) {
		this.projectTag = projectTag;
	}

}