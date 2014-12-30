/**
 * Copyright mysoft Limited (c) 2014. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of mysoft Limited. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from mysoft or an authorized sublicensor.
 */
package com.mysoft.b2b.event.api.event;

import java.io.Serializable;
import java.util.Date;

/**
 * Administrator: 事件
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * Administrator    1.0           2014年8月25日     Created
 *
 * </pre>
 * @since b2b 1.5.0
 */

@SuppressWarnings("serial")
public class Event implements Serializable{
	
	private String eventId;
	
	private String typeCode;
	 
	private String eventContent;
	
	private Integer status;
	
	private String remark;
	
	private String createdBy;
	
	private Date createdTime;
	
	private Date lastDealtTime;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventContent() {
		return eventContent;
	}

	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}

	
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	
	public Date getLastDealtTime() {
		return lastDealtTime;
	}

	public void setLastDealtTime(Date lastDealtTime) {
		this.lastDealtTime = lastDealtTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", typeCode=" + typeCode
				+ ", eventContent=" + eventContent + ", status=" + status
				+ ", remark=" + remark + ", createdBy=" + createdBy
				+ ", createdTime=" + createdTime + ", lastDealtTime="
				+ lastDealtTime + "]";
	}

	public Event() {
	}
	
}
