package com.mysoft.b2b.event.provider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysoft.b2b.event.BaseTestCase;
import com.mysoft.b2b.event.api.EventCenterService;
import com.mysoft.b2b.event.api.app.App;
import com.mysoft.b2b.event.api.app.AppSubscribe;
import com.mysoft.b2b.event.api.event.Event;
import com.mysoft.b2b.event.api.event.EventStatus;

@RunWith(SpringJUnit4ClassRunner.class)
public class EventCenterServiceTestCase extends BaseTestCase {

	private static final Logger log = Logger.getLogger(EventCenterServiceTestCase.class);
	
	@Autowired
	private EventCenterService eventCenterService;
	
	@Test
	public void testSaveEvent(){
		log.info("开始测试saveEvent方法");
		Event event = new Event();
		event.setEventId("test_eventId");
		event.setTypeCode("test_typeCode");
		event.setEventContent("test_eventContent");
		event.setStatus(EventStatus.DEFAULT.getValue());
		event.setRemark("test_remark");
		event.setCreatedBy("test_henry");
		event.setCreatedTime(new Date());
		event.setLastDealtTime(new Date());
		
		eventCenterService.saveEvent(event);
	}
	
	@Test
	public void testSaveSubscribe(){
		log.info("开始测试saveSubscribe方法");
		List<AppSubscribe> subscribes = new ArrayList<AppSubscribe>();
		AppSubscribe as1 = new AppSubscribe();
		AppSubscribe as2 = new AppSubscribe();
		as1.setAppId("as1");
		as1.setEventTypeCode("code1");
		as1.setCreatedBy("henry");
		as2.setAppId("as2");
		as2.setEventTypeCode("code2");
		as2.setCreatedBy("henry");
		subscribes.add(as1);
		subscribes.add(as2);
		App app = new App();
		app.setAppId("1");
		eventCenterService.saveSubscribe(app, subscribes);
	}
}
