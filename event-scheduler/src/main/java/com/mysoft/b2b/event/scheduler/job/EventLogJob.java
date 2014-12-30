/**
 * Copyright mysoft Limited (c) 2014. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of mysoft Limited. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from mysoft or an authorized sublicensor.
 */
package com.mysoft.b2b.event.scheduler.job;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.mysoft.b2b.commons.scheduler.MysoftJob;
import com.mysoft.b2b.event.api.app.App;
import com.mysoft.b2b.event.api.app.AppCriteria;
import com.mysoft.b2b.event.api.app.AppService;
import com.mysoft.b2b.event.api.app.ProtocolType;
import com.mysoft.b2b.event.api.event.Event;
import com.mysoft.b2b.event.api.event.EventLog;
import com.mysoft.b2b.event.api.event.EventLogCriteria;
import com.mysoft.b2b.event.api.event.EventLogStatus;
import com.mysoft.b2b.event.api.event.EventService;
import com.mysoft.b2b.event.scheduler.protocol.rmi.RMIClientHelper;
import com.mysoft.b2b.event.scheduler.util.CustomizedPropertyConfigurer;

/**
 * chengp:    推送消息，
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * chengp     1.0           2014年8月28日    Create
 * hedx		  1.0			2014年9月18日    Change
 * </pre>
 * @since b2b 2.0.0
 */

public class EventLogJob extends MysoftJob { 
	
	private static Logger log = Logger.getLogger(EventLogJob.class);
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private AppService appService;
	
    @Override
    public void run() {
    	
    	log.info("异步事件推送消息-->定时任务开始.......");
    	List<EventLog> eventLogs = this.getEventLogs();
    	
    	int total = CollectionUtils.isEmpty(eventLogs)?0:eventLogs.size();
    	log.info("异步事件推送消息-->需要推送的消息总数:"+total);
    	
    	if(0 == total){
    		log.info("异步事件推送消息-->没有消息推送，定时任务完成退出");
    		return;
    	}
    	
    	for(int i=0;i<eventLogs.size();i++){
    		log.info("异步事件推送消息-->第"+(i+1)+"个事件日志开始推送");
    		EventLog eventLog = eventLogs.get(i);
    		try{
    			//更新EventLog的状态
    			this.startToDealJob(eventLog);
    			
        		//推送消息
        		App app = this.getAppById(eventLog.getAppId());
        		if(null == app){
        			continue;
        		}
        		
        		if(ProtocolType.RMI.getValue() == app.getProtocolType()){
        			Event event = eventService.getEvent(eventLog.getEventId());
        			RMIClientHelper.getRMIProtocolService(app.getProtocolAddr()).dealEvent(event);
        		}
        		
        		this.finishToDealJob(true, eventLog, null);
    		}
    		catch(Exception e){
    			this.finishToDealJob(false, eventLog, e);
    		}
    	}
    }
    
    /**
     * 获取需要推送的消息集合
     * @return list 
     */
    public List<EventLog> getEventLogs(){
    	EventLogCriteria criteria = new EventLogCriteria();
    	criteria.setLastDealtTime(new Date());
    	//设置tryTime次数
    	String tryTimes = (String)CustomizedPropertyConfigurer.getContextProperty("tryTimes");
    	try {
			Integer tryTime = Integer.valueOf(tryTimes);
			criteria.setTryTimes(tryTime);
		} catch (Exception e) {
			// TODO: handle exception
			criteria.setTryTimes(10);
		}
    	return eventService.getNotExecuteEventLog(criteria);
    }
    
    /**
     * 获取app对象
     * @param appid 
     */
    private App getAppById(String appId){
    	if(StringUtils.isNotEmpty(appId)){
	    	AppCriteria criteria = new AppCriteria();
	    	criteria.setAppId(appId);
	    	criteria = appService.getAppList(criteria);
	    	if(!CollectionUtils.isEmpty(criteria.getList())){
	    		return criteria.getList().get(0);
	    	}
    	}
    	return null;
    }
    
    /**
     * 更新事件log状态
     * @param 
     */
    private void updateEventLog(EventLog eventLog,EventLogStatus status){
    	if(null != eventLog && null != status){
    		eventLog.setStatus(status.getValue());
    		eventLog.setLastDealtTime(new Date());
			eventService.updateEventLog(eventLog);
    	}
    }
    
    /**
     * 发送log工作执行前的前置方法
     * @param event
     */
    private void startToDealJob(EventLog eventLog){
    	if(null != eventLog){
    		this.updateEventLog(eventLog, EventLogStatus.DEALING);
    	}
    }
    
    /**
     * 发送log工作执行后的后置方法
     * @param flag
     * @param event
     * @param e
     */
    private void finishToDealJob(boolean flag, EventLog eventLog, Exception e){
    	if(null != eventLog){
    		if(flag){
    			eventLog.setRemark("事件发送成功!");
        		this.updateEventLog(eventLog, EventLogStatus.SUCCESS);
        	}else{
        		if(e != null){
        			String message = e.getMessage();
        			if(message.length()>200){
        				message = message.substring(0, 200);
        			}
        			eventLog.setRemark(message);
        		}else{
        			eventLog.setRemark("事件发送失败!");
        		}
        		eventLog.setTryTimes(eventLog.getTryTimes()+1);
        		this.updateEventLog(eventLog, EventLogStatus.FAILED);
        	}
    	}
    }
    
}
