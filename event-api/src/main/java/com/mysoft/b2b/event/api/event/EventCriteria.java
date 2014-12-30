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
 *
 * </pre>
 * @since b2b 2.0.0
 */

@SuppressWarnings("serial")
public class EventCriteria extends AbstractCriteria<Event> {

	private String eventId;
	
	private String typeCode;
	 
	private String eventContent;
	
	private Integer status;
	
	private String remark;
	
	private String createdBy;
	
	private Date createdTime;
	
	private Date lastDealtTime;

    private List<Integer> statuss;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

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
