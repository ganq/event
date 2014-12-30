package com.mysoft.b2b.event.provider;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysoft.b2b.event.BaseTestCase;
import com.mysoft.b2b.event.api.event.Event;
import com.mysoft.b2b.event.api.event.EventCriteria;
import com.mysoft.b2b.event.api.event.EventLog;
import com.mysoft.b2b.event.api.event.EventLogCriteria;
import com.mysoft.b2b.event.api.event.EventLogStatus;
import com.mysoft.b2b.event.api.event.EventService;
import com.mysoft.b2b.event.api.event.EventStatus;

@RunWith(SpringJUnit4ClassRunner.class)
public class EventServiceTestCase extends BaseTestCase {

	private static final Logger log = Logger.getLogger(EventServiceTestCase.class);
	
	@Autowired
	private EventService eventService;
	
	@Test
	public void testInsertEvent(){
		log.info("开始测试insertEvent方法");
		Event event = new Event();
		event.setEventId("test_eventId");
		event.setTypeCode("test_typeCode");
		event.setEventContent("test_eventContent");
		event.setStatus(EventStatus.DEFAULT.getValue());
		event.setRemark("test_remark");
		event.setCreatedBy("test_henry");
		event.setCreatedTime(new Date());
		event.setLastDealtTime(new Date());
		
		eventService.insertEvent(event);
	}
	
	@Test
	public void testGetEventList(){
		log.info("开始测试getEventList方法");
		EventCriteria criteria = new EventCriteria();
		List<Event> list = eventService.getEventList(criteria).getList();
		
		Assert.assertTrue(list.size()>0);
	}
	
	@Test
	public void testUpdateEvent(){
		log.info("开始测试updateEvent方法");
		//得到某个event
		EventCriteria criteria = new EventCriteria();
		Event event = eventService.getEventList(criteria).getList().get(0);
		
		event.setTypeCode("update_typeCode");
		event.setEventContent("update_eventContent");
		event.setStatus(EventStatus.DEFAULT.getValue());
		event.setRemark("update_remark");
		event.setCreatedBy("update_createdBy");
		eventService.updateEvent(event);
		
		//验证
		criteria.setEventId(event.getEventId());
		Event newEvent = eventService.getEventList(criteria).getList().get(0);
		Assert.assertEquals("update_typeCode", newEvent.getTypeCode());
	}
	
	@Test
	public void testGetEvent(){
		log.info("开始测试getEvent方法");
		//得到某个event
		EventCriteria criteria = new EventCriteria();
		Event event = eventService.getEventList(criteria).getList().get(0);
		
		Event newEvent = eventService.getEvent(event.getEventId());
		
		//验证
		Assert.assertTrue(event.getEventId().equals(newEvent.getEventId()));
	}
	
	@Test
	public void testGetEventCount(){
		log.info("开始测试getEventCount方法");
		EventCriteria criteria = new EventCriteria();
		int number = eventService.getEventCount(criteria);
		
		Assert.assertTrue(number>0);
	}
	
	@Test
	public void testDeleteEvent(){
		log.info("开始测试deleteEvent方法");
		//得到某个event
		EventCriteria criteria = new EventCriteria();
		Event event = eventService.getEventList(criteria).getList().get(0);
		
		eventService.deleteEvent(event.getEventId());
		
		//验证
		Event newEvent = eventService.getEvent(event.getEventId());
		Assert.assertTrue(newEvent == null);
	}
	
	@Test
	public void testInsertEventLog(){
		log.info("开始测试insertEventLog");
		EventLog eventLog =  new EventLog();
		eventLog.setEventId("test_eventId");
		eventLog.setAppId("test_appId");
		eventLog.setTypeCode("test_typeCode");
		eventLog.setEventContent("test_eventContent");
		eventLog.setStatus(EventLogStatus.DEFAULT.getValue());
		eventLog.setTryTimes(1);
		eventLog.setRemark("test_remark");
		eventLog.setCreatedBy("test_createdBy");
		eventLog.setCreatedTime(new Date());
		
		eventService.insertEventLog(eventLog);
	}
	
	@Test
	public void testGetEventLogList(){
		log.info("开始测试getEventLogList方法");
		EventLogCriteria criteria = new EventLogCriteria();
		List<EventLog> list = eventService.getEventLogList(criteria).getList();
		
		Assert.assertTrue(list.size()>0);
	}
	
	@Test
	public void testUpdateEventLog(){
		log.info("开始测试updateEventLog方法");
		//得到某个EventLog
		EventLogCriteria criteria = new EventLogCriteria();
		EventLog eventLog = eventService.getEventLogList(criteria).getList().get(0);
		
		eventLog.setEventId("update_eventId");
		eventLog.setAppId("update_appId");
		eventLog.setTypeCode("update_typeCode");
		eventLog.setEventContent("update_eventContent");
		eventLog.setStatus(EventLogStatus.DEFAULT.getValue());
		eventLog.setTryTimes(1);
		eventLog.setRemark("update_remark");
		eventLog.setCreatedBy("update_createdBy");
		eventLog.setCreatedTime(new Date());
		eventLog.setLastDealtTime(new Date());
		eventService.updateEventLog(eventLog);
		
		//验证
		criteria.setEventLogId(eventLog.getEventLogId());
		EventLog newEventLog = eventService.getEventLogList(criteria).getList().get(0);
		Assert.assertEquals("update_eventId", newEventLog.getEventId());
	}
	
	@Test
	public void testGetEventLogCount(){
		log.info("开始测试getEventLogCount方法");
		EventLogCriteria criteria = new EventLogCriteria();
		int number = eventService.getEventLogCount(criteria);
		Assert.assertTrue(number>0);
	}
	
	@Test
	public void testDeleteEventLog(){
		log.info("开始测试deleteEventLog方法");
		//得到某个EventLog
		EventLogCriteria criteria = new EventLogCriteria();
		EventLog eventLog = eventService.getEventLogList(criteria).getList().get(0);
		
		eventService.deleteEventLog(eventLog);
		
		//验证
		criteria.setEventLogId(eventLog.getEventLogId());
		List<EventLog> list = eventService.getEventLogList(criteria).getList();
		Assert.assertTrue(list.size()==0);
	}
	
	/*
	@Test
	public void testGetNotExecuteEvent(){
		log.info("开始测试getNotExecuteEvent方法");
		EventCriteria criteria = new EventCriteria();
		criteria.setLastDealtTime(new Date());
		List<Event> list = eventService.getNotExecuteEvent(criteria);
		Assert.assertEquals(1, list.size());
	}
	*/
	
	/*
	@Test
	public void testGetNotExecuteEventLog(){
		log.info("开始测试getNotExecuteEventLog方法");
		EventLogCriteria criteria = new EventLogCriteria();
		criteria.setTryTimes(5);
		criteria.setLastDealtTime(new Date());
		List<EventLog> list = eventService.getNotExecuteEventLog(criteria);
		Assert.assertEquals(16, list.size());
	}
	*/
}
