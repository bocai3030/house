package org.house.db.entity.solr;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@SolrDocument(solrCoreName = "collection1")
public class ProjectTagSolr extends AbstractSolrBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotNull
	@Field("project_id")
	protected String projectId; // 项目ID
	@Field("focus_status")
	protected String focusStatus; // 关注状态
	@Field("remark")
	protected String remark; // 备注

	@Override
	public int hashCode() {
		int result = 17;
		if (this.projectId != null) {
			result = 31 * result + this.projectId.hashCode();
		}
		if (this.focusStatus != null) {
			result = 31 * result + this.focusStatus.hashCode();
		}
		if (this.remark != null) {
			result = 31 * result + this.remark.hashCode();
		}
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof ProjectTagSolr) {
			final ProjectTagSolr anObject = (ProjectTagSolr) obj;
			if (ObjectUtils.nullSafeEquals(this.projectId, anObject.getProjectId())
					&& ObjectUtils.nullSafeEquals(this.focusStatus, anObject.getFocusStatus())
					&& ObjectUtils.nullSafeEquals(this.remark, anObject.getRemark())) {
				return true;
			}
		}
		return false;
	}

	public void fromObj(final ProjectTagSolr theObj) {
		// this.projectId = theObj.projectId; // NO ID
		this.focusStatus = theObj.focusStatus;
		this.remark = theObj.remark;
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(final String projectId) {
		this.projectId = projectId;
	}

	public String getFocusStatus() {
		return this.focusStatus;
	}

	public void setFocusStatus(final String focusStatus) {
		this.focusStatus = focusStatus;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(final String remark) {
		this.remark = remark;
	}

}