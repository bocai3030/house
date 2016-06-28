package org.house.db.entity.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(name = "project_tag")
public class ProjectTag extends AbstractJpaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotNull
	protected String projectId; // 项目ID
	protected String focusStatus; // 关注状态
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
		if (obj instanceof ProjectTag) {
			final ProjectTag anObject = (ProjectTag) obj;
			if (ObjectUtils.nullSafeEquals(this.projectId, anObject.getProjectId())
					&& ObjectUtils.nullSafeEquals(this.focusStatus, anObject.getFocusStatus())
					&& ObjectUtils.nullSafeEquals(this.remark, anObject.getRemark())) {
				return true;
			}
		}
		return false;
	}

	public void fromObj(final ProjectTag theObj) {
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