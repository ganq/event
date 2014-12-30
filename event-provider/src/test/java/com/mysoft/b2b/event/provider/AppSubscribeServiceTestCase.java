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
import com.mysoft.b2b.event.api.app.AppSubscribe;
import com.mysoft.b2b.event.api.app.AppSubscribeCriteria;
import com.mysoft.b2b.event.api.app.AppSubscribeService;
import com.mysoft.b2b.event.api.app.AppSubscribeStatus;

@RunWith(SpringJUnit4ClassRunner.class)
public class AppSubscribeServiceTestCase extends BaseTestCase{
	
	private static final Logger log = Logger.getLogger(AppSubscribeServiceTestCase.class);

	@Autowired
	private AppSubscribeService appSubscribeService;
	
	@Test
	public void testInsert(){
		log.info("开始测试 insert方法");
		AppSubscribe appSubscribe = new AppSubscribe();
		appSubscribe.setAppId("test_appId");
		appSubscribe.setEventTypeCode("test_eventTypeCode");
		appSubscribe.setStatus(AppSubscribeStatus.Steady.getValue());
		appSubscribe.setCreatedBy("test_henry");
		appSubscribe.setCreatedTime(new Date());
		appSubscribe.setLastModifiedBy("test_henry");
		appSubscribe.setLastModifiedTime(new Date());
		
		appSubscribeService.insert(appSubscribe);
	}
	
	
	@Test
	public void testGetAppSubscribeList(){
		log.info("开始测试getAppSubscribeList方法");
		AppSubscribeCriteria criteria = new AppSubscribeCriteria();
		List<AppSubscribe> list = appSubscribeService.getAppSubscribeList(criteria).getList();
		
		Assert.assertTrue(list.size()>0);
	}
	
	@Test
	public void testUpdate(){
		log.info("开始测试update方法");
		//得到某个appSubscribe
		AppSubscribeCriteria criteria = new AppSubscribeCriteria();
		AppSubscribe appSubscribe = appSubscribeService.getAppSubscribeList(criteria).getList().get(0);
		
		appSubscribe.setAppId("update_appId");
		appSubscribe.setEventTypeCode("update_typecode");
		appSubscribe.setStatus(AppSubscribeStatus.Steady.getValue());
		appSubscribe.setCreatedBy("update_henry");
		appSubscribe.setCreatedTime(new Date());
		appSubscribe.setLastModifiedBy("update_henry");
		appSubscribe.setLastModifiedTime(new Date());
		appSubscribeService.update(appSubscribe);
		
		//验证
		criteria.setSubscribeId(appSubscribe.getSubscribeId());
		AppSubscribe newAppSubscribe = appSubscribeService.getAppSubscribeList(criteria).getList().get(0);
		Assert.assertEquals("update_appId", newAppSubscribe.getAppId());
	}
	
	@Test
	public void testGetAppSubscribeCount(){
		log.info("开始测试getAppSubscribeCount方法");
		AppSubscribeCriteria criteria = new AppSubscribeCriteria();
		int number = appSubscribeService.getAppSubscribeCount(criteria);
		Assert.assertTrue(number>0);
	}
	
	@Test
	public void testDelete(){
		log.info("开始测试delete方法");
		//得到某个appSubscribe
		AppSubscribeCriteria criteria = new AppSubscribeCriteria();
		AppSubscribe appSubscribe = appSubscribeService.getAppSubscribeList(criteria).getList().get(0);
		
		appSubscribeService.delete(appSubscribe.getSubscribeId());
		
		//验证
		criteria.setSubscribeId(appSubscribe.getSubscribeId());
		List<AppSubscribe> list = appSubscribeService.getAppSubscribeList(criteria).getList();
		Assert.assertTrue(list.size()==0);
	}
}
