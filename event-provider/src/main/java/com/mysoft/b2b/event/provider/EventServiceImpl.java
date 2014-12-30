/**
 * Copyright mysoft Limited (c) 2014. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of mysoft Limited. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from mysoft or an authorized sublicensor.
 */
package com.mysoft.b2b.event.provider;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysoft.b2b.bizsupport.api.IdGenerationService;
import com.mysoft.b2b.commons.exception.PlatformUncheckException;
import com.mysoft.b2b.event.api.event.Event;
import com.mysoft.b2b.event.api.event.EventCriteria;
import com.mysoft.b2b.event.api.event.EventLog;
import com.mysoft.b2b.event.api.event.EventLogCriteria;
import com.mysoft.b2b.event.api.event.EventService;
import com.mysoft.b2b.event.mapper.EventMapper;
import com.mysoft.b2b.event.util.PageUtil;

/**
 * chengp: Change to the actual description of this class
 * 
 * @version Revision History
 * 
 *          <pre>
 * Author     Version       Date        Changes
 * chengp    1.0           2014年8月27日     Created
 * hedx      1.0           2014年9月11日     Enhancement
 *
 * </pre>
 * @since b2b 2.0.0
 */
@Service("eventService")
public class EventServiceImpl implements EventService {

	private static final Logger log = Logger.getLogger(EventServiceImpl.class);
	
	@Autowired
	private IdGenerationService idGenerationService;
	
	@Autowired
	private EventMapper eventMapper;

	@Override
	public void insertEvent(Event event) {
		log.debug("...insertEvent  start event ====>" + event);
		if (null == event || null == event.getStatus()) {
			throw new PlatformUncheckException(
					"insertEvent 参数(getStatus,getCreatedBy,,getEventContent)不能为NULL",
					null);
		}
		long id = idGenerationService.getNextId("b2b_support.bizp_event");

		event.setEventId(id + "");
		eventMapper.insertEvent(event);
	}

	@Override
	public void updateEvent(Event event) {
		log.debug("...updateEvent  start event ====>" + event);
		if (null == event || StringUtils.isEmpty(event.getEventId())) {
			throw new PlatformUncheckException(
					"update 参数(event)不能为NULL", null);
		}
		eventMapper.updateEvent(event);
	}

	@Override
	public void deleteEvent(String eventId) {
		log.debug("...deleteEvent  start eventId ====>" + eventId);
		if (StringUtils.isEmpty(eventId)) {
			throw new PlatformUncheckException(
					"deleteEvent 参数(eventId)不能为NULL", null);
		}
		eventMapper.deleteEvent(eventId);
	}

	@Override
	public Event getEvent(String eventId) {
		log.debug("getEvent start  eventId ====> " + eventId);
		if (StringUtils.isEmpty(eventId)) {
			throw new PlatformUncheckException("getEvent 参数(eventId)不能为NULL",
					null);
		}
		return eventMapper.getEvent(eventId);
	}
	
	@Override
	public EventCriteria getEventList(EventCriteria criteria) {
		log.debug("...getEventList start criteria ====>" + criteria);

		if (null == criteria) {
			throw new PlatformUncheckException("getEventList 参数不能为NULL", null);
		}

		int size = eventMapper.getEventCount(criteria);

		criteria.setOffset(PageUtil.getPageOffset(criteria.getPageSize(),
				criteria.getCurrentPage()));
		criteria.setTotalRows(size);

		List<Event> lt = eventMapper.getEventList(criteria);

		criteria.setList(lt);
		return criteria;
	}

	@Override
	public int getEventCount(EventCriteria criteria) {
		return eventMapper.getEventCount(criteria);
	}

	@Override
	public void insertEventLog(EventLog eventLog) {
		log.debug("...insertEventLog  start EventLog ====>" + eventLog);
		if (null == log || StringUtils.isEmpty(eventLog.getEventId())
				|| StringUtils.isEmpty(eventLog.getTypeCode())
				|| null == eventLog.getStatus()

		) {
			throw new PlatformUncheckException(
					"insert 参数(getTypeCode,getEventContent,getStatus,getEventId)不能为NULL",
					null);
		}
		long id = idGenerationService.getNextId("b2b_support.bizp_event_log");

		eventLog.setEventLogId(id + "");
		eventMapper.insertEventLog(eventLog);
	}

	@Override
	public void updateEventLog(EventLog eventLog) {
		log.debug("...updateEventLog start eventLog ====>" + eventLog);
		if (null == log || StringUtils.isEmpty(eventLog.getEventId())
				|| StringUtils.isEmpty(eventLog.getTypeCode())
				|| null == eventLog.getStatus()) {
			throw new PlatformUncheckException(
					"update 参数(getTypeCode,getEventContent,getStatus,getEventId)不能为NULL",
					null);
		}
		eventMapper.updateEventLog(eventLog);
	}
	
	@Override
	public void deleteEventLog(String eventLogId) {
		log.debug("...deleteEventLog  start eventLogId ====>" + eventLogId);
		if (null == eventLogId) {
			throw new PlatformUncheckException(
					"deleteEventLog 参数(eventLogId)不能为NULL", null);
		}
		eventMapper.deleteEventLog(eventLogId);
	}

	@Override
	public void deleteEventLog(EventLog eventLog) {
		log.debug("...deleteEventLog  start eventLogId ====>" + eventLog);
		if (null == eventLog) {
			throw new PlatformUncheckException(
					"deleteEventLog 参数(eventLog)不能为NULL", null);
		}
		deleteEventLog(eventLog.getEventLogId());
	}

	@Override
	public EventLogCriteria getEventLogList(EventLogCriteria criteria) {
		log.debug("...getEventLogList start criteria ====>" + criteria);

		if (null == criteria) {
			throw new PlatformUncheckException("getEventLogList 参数不能为NULL",
					null);
		}

		int size = eventMapper.getEventLogCount(criteria);

		criteria.setOffset(PageUtil.getPageOffset(criteria.getPageSize(),
				criteria.getCurrentPage()));
		criteria.setTotalRows(size);

		List<EventLog> lt = eventMapper.getEventLogList(criteria);

		criteria.setList(lt);
		return criteria;
	}

	@Override
	public int getEventLogCount(EventLogCriteria criteria) {
		return eventMapper.getEventLogCount(criteria);
	}

	@Override
	public List<Event> getNotExecuteEvent(EventCriteria criteria) {
		log.debug("...getNotExecuteEvent start ====>"+ criteria);
		if (null == criteria) {
			throw new PlatformUncheckException(
					"getNotExecuteEvent 参数不能为NULL", null);
		}
		List<Event> lt = eventMapper.getNotExecuteEvent(criteria);
		return lt;
	}

	@Override
	public List<EventLog> getNotExecuteEventLog(EventLogCriteria criteria) {
		log.debug("...getNotExecuteEventLog start criteria ====>" + criteria);
		if (null == criteria) {
			throw new PlatformUncheckException(
					"getNotExecuteEventLog 参数不能为NULL", null);
		}
		List<EventLog> lt = eventMapper.getNotExecuteEventLog(criteria);
		return lt;
	}

}
