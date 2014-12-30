package com.mysoft.b2b.event.provider;

import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysoft.b2b.event.BaseTestCase;
import com.mysoft.b2b.event.api.event.EventType;
import com.mysoft.b2b.event.api.event.EventTypeCriteria;
import com.mysoft.b2b.event.api.event.EventTypeService;

@RunWith(SpringJUnit4ClassRunner.class)
public class EventTypeServiceTestCase extends BaseTestCase{

	private static final Logger log = Logger.getLogger(EventTypeServiceTestCase.class);
	
	@Autowired
	private EventTypeService eventTypeService;
	
	@Test
	public void testGetEventTypeList(){
		log.info("开始测试getEventTypeList方法");
		EventTypeCriteria criteria = new EventTypeCriteria();
		List<EventType> list = eventTypeService.getEventTypeList(criteria).getList();
		Assert.assertTrue(list.size()>0);
	}
	
}
