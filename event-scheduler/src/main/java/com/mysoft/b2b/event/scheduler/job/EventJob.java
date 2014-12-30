/**
 * Copyright mysoft Limited (c) 2014. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of mysoft Limited. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from mysoft or an authorized sublicensor.
 */
package com.mysoft.b2b.event.scheduler.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.mysoft.b2b.commons.scheduler.MysoftJob;
import com.mysoft.b2b.event.api.app.AppSubscribe;
import com.mysoft.b2b.event.api.app.AppSubscribeCriteria;
import com.mysoft.b2b.event.api.app.AppSubscribeService;
import com.mysoft.b2b.event.api.event.Event;
import com.mysoft.b2b.event.api.event.EventCriteria;
import com.mysoft.b2b.event.api.event.EventLog;
import com.mysoft.b2b.event.api.event.EventLogStatus;
import com.mysoft.b2b.event.api.event.EventService;
import com.mysoft.b2b.event.api.event.EventStatus;

/**
 * chengp:    按事件订阅，解析消息成为LOG
 * @version   Revision History
 * <pre>
 * Author    Version       Date        Changes
 * chengp    1.0           2014年8月28日     Created
 *
 * </pre>
 * @since b2b 2.0.0
 */
public class EventJob extends MysoftJob {
	
	private static Logger log = Logger.getLogger(EventJob.class);
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private AppSubscribeService appSubscribeService;
	
    @Override
    public void run() {
    	log.info("异步事件解析消息成为LOG-->定时任务开始.......");
    	
    	//1. 得到所有未处理的事件,没有则直接返回
    	List<Event> notExecuteEvents = this.getNotExecuteEvents();
    	int eventNumber = CollectionUtils.isEmpty(notExecuteEvents)?0:notExecuteEvents.size();
    	log.info("异步事件解析消息成为LOG-->需要处理的事件总数："+eventNumber);
    	if(0 == eventNumber){
    		log.info("异步事件解析消息成为LOG-->没有事件需要解析成log，定时任务完成退出");
    		return;
    	}
    	
    	//2. 得到所有的app订阅事件关系 Map<String(eventType),List<AppSubscribe>>
    	List<AppSubscribe> appSubscribes = this.getAppSubscribes();
    	if(CollectionUtils.isEmpty(appSubscribes)){
    		log.info("异步事件解析消息成为LOG-->没有app订阅事件，定时任务完成退出");
    		return;
    	}
    	Map<String,List<AppSubscribe>> subscripeGroup = this.getAppSubGroup(appSubscribes);
    	
    	//3. 依次执行未处理的事件: 前置工作--》解析为log--》后置工作
    	for(int i=0;i<notExecuteEvents.size();i++){
    		log.info("异步事件解析消息成为LOG-->第"+(i+1)+"个事件开始解析为log");
    		Event event = notExecuteEvents.get(i);
			try{
				//前置工作:设置状态为dealing，设置最后处理时间
				this.startToDealJob(event);
				
				//解析事件为log
				boolean flag = this.parseLog(event, subscripeGroup.get(event.getTypeCode()));
				
				//后置工作:设置状态为success，设置最后处理时间
				if(flag){
					this.finishToDealJob(true,event,null);
				}else{
					this.finishToDealJob(false, event, null);
				}
			}catch(Exception e){
    			this.finishToDealJob(false, event, e);
    		}
		}
		log.info("异步事件解析消息成为LOG-->定时任务完成退出");
    }
    
    
    /**
     * 获取未执行event集合
     * @param 
     */
    private List<Event> getNotExecuteEvents(){
    	EventCriteria criteria = new EventCriteria();
    	criteria.setCurrentPage(1);
    	criteria.setLastDealtTime(new Date());
    	return eventService.getNotExecuteEvent(criteria);
    }
    
    
    /**
     * 获取app订阅集合
     * @param 
     */
    private List<AppSubscribe> getAppSubscribes(){
    	AppSubscribeCriteria criteria = new AppSubscribeCriteria();
    	criteria.setOffset(0);
    	criteria.setPageSize(500);
    	criteria = appSubscribeService.getAppSubscribeList(criteria);
    	return criteria.getList();
    }
    
    /**
     * 更新事件状态
     * @param 
     */
    private void updateEvent(Event event,EventStatus status){
    	if(null != event && null != status){
    		event.setStatus(status.getValue());
			event.setLastDealtTime(new Date());
			eventService.updateEvent(event);
    	}
    }
    
    /**
     * 解析log工作执行前的前置方法
     * @param event
     */
    private void startToDealJob(Event event){
    	if(null != event){
    		this.updateEvent(event, EventStatus.DEALING);
    	}
    } 
    
    
    /**
     * 解析log工作执行后的后置方法
     * @param flag
     * @param event
     * @param e
     */
    private void finishToDealJob(boolean flag, Event event, Exception e){
    	if(null != event){
    		if(flag){
        		event.setRemark("事件解析为日志成功!");
        		this.updateEvent(event, EventStatus.SUCCESS);
        	}else{
        		if(e != null){
        			if(e.getMessage().length()>200){
        				event.setRemark(e.getMessage().substring(0, 200));
        			}else{
        				event.setRemark(e.getMessage());
        			}
        		}else{
        			event.setRemark("事件解析为日志失败!");
        		}
        		this.updateEvent(event, EventStatus.FAILED);
        	}
    	}
    }
    
    /**
     * app订阅集合按照事件类型分组
     * @param list 
     */
    private Map<String,List<AppSubscribe>> getAppSubGroup(List<AppSubscribe> appSubscribes){
    	Map<String,List<AppSubscribe>> group = new HashMap<String, List<AppSubscribe>>();
    	if(!CollectionUtils.isEmpty(appSubscribes)){
    		for(AppSubscribe sub : appSubscribes){
    			if(group.containsKey(sub.getEventTypeCode())){
    				group.get(sub.getEventTypeCode()).add(sub);
    			}else{
    				List<AppSubscribe> element = new ArrayList<AppSubscribe>();
    				element.add(sub);
    				group.put(sub.getEventTypeCode(), element);
    			}
    		}
    	}
    	return group;
    }
    
    /**
     *解析事件为log
     *@param event 事件
     *@param appSubscribes 订阅该事件类型的app集合
     */
    private boolean parseLog(Event event,List<AppSubscribe> appSubscribes){
		if(!CollectionUtils.isEmpty(appSubscribes)){
    		for(AppSubscribe sub:appSubscribes){
    			EventLog eventLog = new EventLog();
    			eventLog.setCreatedBy(event.getCreatedBy());
    			eventLog.setCreatedTime(new Date());
    			eventLog.setEventContent(event.getEventContent());
    			eventLog.setEventId(event.getEventId());
    			//eventLog.setRemark(event.getRemark()); //eventLog的remark应该只记录本身的情况
    			eventLog.setStatus(EventLogStatus.DEFAULT.getValue());
    			eventLog.setTypeCode(event.getTypeCode());
    			eventLog.setAppId(sub.getAppId());
        		eventService.insertEventLog(eventLog);
        		log.info("异步事件解析消息成为LOG-->事件解析log成功，事件ID："+event.getEventId()+" appid:"+sub.getAppId());
    		}
    		return true;
    	}else{
    		log.info("异步事件解析消息成为LOG-->事件类型:"+event.getTypeCode()+",没有app订阅，事件不能解析成log");
    	}
		return false;
    }

}
