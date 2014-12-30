package com.mysoft.b2b.event.scheduler.job;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysoft.b2b.event.scheduler.BaseTestCase;
import com.mysoft.b2b.event.scheduler.job.EventLogJob;

@RunWith(SpringJUnit4ClassRunner.class)
public class EventLogJobTestCase extends BaseTestCase{
	
	@Autowired
	private EventLogJob eventLogJob;
	
	@Test
	public void testRun(){
		eventLogJob.run();
	}
}
