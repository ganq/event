/**
 * Copyright mysoft Limited (c) 2014. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of mysoft Limited. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from mysoft or an authorized sublicensor.
 */
package com.mysoft.b2b.event.api.app;

import java.io.Serializable;
import java.util.Date;

/**
 * Administrator: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * Administrator    1.0           2014年8月21日     Created
 * </pre>
 * @since b2b 2.0.0
 */

@SuppressWarnings("serial")
public class App implements Serializable {

    private String appId;

    private String appCode;

    private String appName;

    private int protocolType;

    private String protocolAddr;

    private String createdBy;

    private String lastModifiedBy;

    private Date createdTime;

    private Date lastModifiedTime;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(int protocolType) {
        this.protocolType = protocolType;
    }

    public String getProtocolAddr() {
        return protocolAddr;
    }

    public void setProtocolAddr(String protocolAddr) {
        this.protocolAddr = protocolAddr;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

	@Override
	public String toString() {
		return "App [appId=" + appId + ", appCode=" + appCode + ", appName="
				+ appName + ", protocolType=" + protocolType
				+ ", protocolAddr=" + protocolAddr + ", createdBy=" + createdBy
				+ ", lastModifiedBy=" + lastModifiedBy + ", createdTime="
				+ createdTime + ", lastModifiedTime=" + lastModifiedTime + "]";
	}
    
}
