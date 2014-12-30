/**
 * Copyright mysoft Limited (c) 2014. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of mysoft Limited. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from mysoft or an authorized sublicensor.
 */
package com.mysoft.b2b.event.mapper;

import java.util.List;

import com.mysoft.b2b.event.api.event.Event;
import com.mysoft.b2b.event.api.event.EventCriteria;
import com.mysoft.b2b.event.api.event.EventLog;
import com.mysoft.b2b.event.api.event.EventLogCriteria;


/**
 * Administrator: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * Administrator    1.0           2014年8月26日     Created
 *
 * </pre>
 * @since b2b 1.5.0
 */

public interface EventMapper {
	
		public void insertEvent(Event event);
		
	    public void updateEvent(Event event) ;
	    
	    public void deleteEvent(String eventId);
	    
	    public Event getEvent(String eventId);
	    
	    public List<Event> getEventList(EventCriteria criteria);
	    
	    public int getEventCount(EventCriteria criteria);
	    
	    public void insertEventLog(EventLog log);
	    
	    public void deleteEventLog(String eventLogId);
	    
	    public void updateEventLog(EventLog log);
	    
	    public List<EventLog> getEventLogList(EventLogCriteria criteria);
	    
	    public int getEventLogCount(EventLogCriteria criteria);
	    
	    public List<Event> getNotExecuteEvent(EventCriteria criteria);
	    
	    public List<EventLog> getNotExecuteEventLog(EventLogCriteria criteria);
	
}	
