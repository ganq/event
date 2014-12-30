/**
 * Copyright mysoft Limited (c) 2014. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of mysoft Limited. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from mysoft or an authorized sublicensor.
 */
package com.mysoft.b2b.event.api.event;

import java.util.List;

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

public interface EventService {
    /**
     * 
     * @param event
     */
    public void insertEvent(Event event);

    /**
     * 
     * @param event
     */
    public void updateEvent(Event event);

    /**
     * 
     * @param eventId
     */
    public void deleteEvent(String eventId);
    
    /**
     * 
     * @param eventId
     */
    public Event getEvent(String eventId);


    /**
     * 
     * @param criteria
     * @return
     */
    public EventCriteria getEventList(EventCriteria criteria);

    /**
     * 
     * @param criteria
     * @return
     */
    public int getEventCount(EventCriteria criteria);
    
    /**
     * 
     * @param log
     */
    public void insertEventLog(EventLog eventLog);
    
    /**
     * 
     * @param log
     */
    public void updateEventLog(EventLog eventLog);

    /**
     * 
     * @param eventLogId
     */
    public void deleteEventLog(String eventLogId);

    /**
     * 
     * @param log
     */
    public void deleteEventLog(EventLog eventLog);

    /**
     * 
     * @param criteria
     * @return
     */
    public EventLogCriteria getEventLogList(EventLogCriteria criteria);

    /**
     * 
     * @param criteria
     * @return
     */
    public int getEventLogCount(EventLogCriteria criteria);

    /**
     * 获取所有未执行的事件
     * 需要按时间先后顺序排序
     * @return
     */
    public List<Event> getNotExecuteEvent(EventCriteria criteria);
    
    /**
     * 获取所有未推送的事件log
     * 需要按时间先后顺序排序
     * @param event
     * @return
     */
    public List<EventLog> getNotExecuteEventLog(EventLogCriteria criteria);
    
}
