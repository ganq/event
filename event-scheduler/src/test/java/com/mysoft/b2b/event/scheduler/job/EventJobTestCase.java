package com.mysoft.b2b.event.scheduler.job;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysoft.b2b.event.scheduler.BaseTestCase;
import com.mysoft.b2b.event.scheduler.job.EventJob;

@RunWith(SpringJUnit4ClassRunner.class)
public class EventJobTestCase extends BaseTestCase{
	
	@Autowired
	private EventJob eventJob;
	
	@Test
	public void testEventJob(){
		eventJob.run();
	}
}
