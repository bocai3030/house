package org.house.db.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.house.util.C;
import org.house.util.JsonHelper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

@JsonAutoDetect
@MappedSuperclass
public abstract class AbstractJpaBean {
	@Id
	@GeneratedValue()
	@JsonIgnore
	protected Long id;

	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateSerializer.class)
	@JsonFormat(pattern = C.DATE_JSON_FORMAT_PATTERN, timezone = C.DATE_JSON_FORMAT_TIMEZONE)
	protected Date createTime;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateSerializer.class)
	@JsonFormat(pattern = C.DATE_JSON_FORMAT_PATTERN, timezone = C.DATE_JSON_FORMAT_TIMEZONE)
	protected Date updateTime;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(final Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(final Date updateTime) {
		this.updateTime = updateTime;
	}

	@PrePersist
	protected void preSave() {
		this.createTime = new Date();
		this.updateTime = this.createTime;
	}

	@PreUpdate
	protected void preUpdate() {
		this.updateTime = new Date();
	}

	@Override
	public String toString() {
		return JsonHelper.gson.toJson(this);
	}
}