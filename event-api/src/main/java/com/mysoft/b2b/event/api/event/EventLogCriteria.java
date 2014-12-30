/**
 * Copyright mysoft Limited (c) 2014. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of mysoft Limited. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from mysoft or an authorized sublicensor.
 */
package com.mysoft.b2b.event.api.event;

import java.util.Date;
import java.util.List;

import com.mysoft.b2b.event.api.util.AbstractCriteria;

/**
 * chengp: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * chengp    1.0           2014年8月27日     Created
 * hedx		 1.0		   2014年9月11日     Enhancement
 * </pre>
 * @since b2b 2.0.0
 */

@SuppressWarnings("serial")
public class EventLogCriteria extends AbstractCriteria<EventLog> {

	private String eventLogId;
	
    private String eventId;
    
    private String appId;

    private String typeCode;

    private String eventContent;

    private Integer status;
    
    private Integer tryTimes;
    
    private String remark;
    
    private String createdBy;
    
    private Date createdTime;

    private Date lastDealtTime;
    
    private Date lastDealtTimeStart;
    
    private Date lastDealtTimeEnd;

    private List<Integer> statuss;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTryTimes() {
		return tryTimes;
	}

	public void setTryTimes(Integer tryTimes) {
		this.tryTimes = tryTimes;
	}

	public Date getLastDealtTime() {
        return lastDealtTime;
    }

    public void setLastDealtTime(Date lastDealtTime) {
        this.lastDealtTime = lastDealtTime;
    }

    public List<Integer> getStatuss() {
        return statuss;
    }

    public void setStatuss(List<Integer> statuss) {
        this.statuss = statuss;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Date getLastDealtTimeStart() {
        return lastDealtTimeStart;
    }

    public void setLastDealtTimeStart(Date lastDealtTimeStart) {
        this.lastDealtTimeStart = lastDealtTimeStart;
    }

    public Date getLastDealtTimeEnd() {
        return lastDealtTimeEnd;
    }

    public void setLastDealtTimeEnd(Date lastDealtTimeEnd) {
        this.lastDealtTimeEnd = lastDealtTimeEnd;
    }

	public String getEventLogId() {
		return eventLogId;
	}

	public void setEventLogId(String eventLogId) {
		this.eventLogId = eventLogId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}
