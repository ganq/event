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
import com.mysoft.b2b.event.api.app.App;
import com.mysoft.b2b.event.api.app.AppCriteria;
import com.mysoft.b2b.event.api.app.AppService;
import com.mysoft.b2b.event.api.app.ProtocolType;



@RunWith(SpringJUnit4ClassRunner.class)
public class AppServiceTestCase extends BaseTestCase{

	private static final Logger log = Logger.getLogger(AppServiceTestCase.class);
	
	@Autowired
	private AppService appService;
	
	@Test
	public void testInsert(){
		log.info("开始测试insert方法");
		App app = new App();
		app.setAppCode("test_appCode");
		app.setAppName("test_appName");
		app.setProtocolType(ProtocolType.RMI.getValue());
		app.setProtocolAddr("rmi://localhost:1099");
		app.setCreatedBy("test_henry");
		app.setCreatedTime(new Date());
		app.setLastModifiedBy("test_henry");
		app.setLastModifiedTime(new Date());
		try{
			appService.insert(app);
			Assert.assertTrue(true);
		}catch(Exception e){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testGetAppList(){
		log.info("开始测试getAppList方法");
		AppCriteria criteria = new AppCriteria();
		criteria.setAppCode("test_appCode");
		criteria.setAppName("test_appName");
		criteria.setCreatedBy("test_henry");
		List<App> list = appService.getAppList(criteria).getList();
		Assert.assertTrue(list.size()>0);
	}
	
	@Test
	public void testGet(){
		log.info("开始测试get方法");
		//随机得到某个app
		AppCriteria criteria = new AppCriteria();
		criteria.setAppCode("test_appCode");
		criteria.setAppName("test_appName");
		criteria.setCreatedBy("test_henry");
		App app = appService.getAppList(criteria).getList().get(0);
		//根据id得到app
		App newApp = appService.get(app.getAppId());
		
		Assert.assertTrue(app.getAppCode().equals(newApp.getAppCode()));
	}
	
	@Test
	public void testUpdate(){
		log.info("开始测试update方法");
		//随机得到某个app
		AppCriteria criteria = new AppCriteria();
		criteria.setAppCode("test_appCode");
		criteria.setAppName("test_appName");
		criteria.setCreatedBy("test_henry");
		App app = appService.getAppList(criteria).getList().get(0);
		//update这个app
		app.setAppName("updated_appName");
		appService.update(app);
		//验证
		App newApp = appService.get(app.getAppId());
		Assert.assertEquals("updated_appName", newApp.getAppName());
	}
	
	@Test
	public void testGetAppCount(){
		log.info("开始测试getAppCount方法");
		AppCriteria criteria = new AppCriteria();
		int number = appService.getAppCount(criteria);
		Assert.assertTrue(number>0);
	}
	
	@Test
	public void testDelete(){
		log.info("开始测试delete方法");
		//得到某个App
		AppCriteria criteria = new AppCriteria();
		criteria.setAppCode("test_appCode");
		criteria.setAppName("test_appName");
		criteria.setCreatedBy("test_henry");
		App app = appService.getAppList(criteria).getList().get(0);
		
		appService.delete(app.getAppId());
	}

}
