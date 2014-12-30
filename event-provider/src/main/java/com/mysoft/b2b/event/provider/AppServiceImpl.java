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
import org.springframework.transaction.annotation.Transactional;

import com.mysoft.b2b.bizsupport.api.IdGenerationService;
import com.mysoft.b2b.commons.exception.PlatformUncheckException;
import com.mysoft.b2b.event.api.app.App;
import com.mysoft.b2b.event.api.app.AppCriteria;
import com.mysoft.b2b.event.api.app.AppService;
import com.mysoft.b2b.event.mapper.AppMapper;
import com.mysoft.b2b.event.util.PageUtil;

/**
 * chengp: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * chengp    1.0           2014年8月27日     Created
 * hedx		 2.0		   2014年9月10日     Enhancement
 * </pre>
 * @since b2b 2.0.0
 */
@Service("appService")
public class AppServiceImpl implements AppService {
	
	private static final Logger log = Logger.getLogger(AppServiceImpl.class);
	
	@Autowired
	private IdGenerationService idGenerationService;
	
	@Autowired
	private AppMapper appMapper;
	
    @Override
    @Transactional
    public App insert(App app) {
    	log.debug("...insert start app ====>"+app);
        if(null == app 
    			|| StringUtils.isEmpty(app.getAppName())
    			|| StringUtils.isEmpty(app.getAppCode())
    					|| StringUtils.isEmpty(app.getCreatedBy())
    							|| StringUtils.isEmpty(app.getProtocolAddr())
    			){
    		throw new PlatformUncheckException("insert 参数(getAppName,getAppCode,getProtocolAddr,getCreatedBy)不能为NULL", null);
    		}
        long id = idGenerationService.getNextId("b2b_support.bizp_event");
        app.setAppId(id+"");
    	appMapper.insert(app);
    	return app;
    }

    @Override
    @Transactional
    public void update(App app) {
    	log.debug("...update  start app ====>"+app);
        if(null == app 
    			|| StringUtils.isEmpty(app.getAppName())
    			|| StringUtils.isEmpty(app.getAppCode())
    							|| StringUtils.isEmpty(app.getProtocolAddr())
    									||  StringUtils.isEmpty(app.getAppId())
    			){
    		throw new PlatformUncheckException("update 参数(getAppName,getAppCode,getProtocolAddr,getAppId)不能为NULL", null);
    		}

    	appMapper.update(app);

    }

    @Override
    @Transactional
    public void delete(String appId) {
    	log.debug("...delete  start appId ====>"+appId);
        if(StringUtils.isEmpty(appId)){
    		throw new PlatformUncheckException("delete 参数(AppId)不能为NULL", null);
    	}
    	appMapper.delete(appId);

    }

    @Override
    public AppCriteria getAppList(AppCriteria criteria) {
    	log.debug("...getAppList start criteria ====>"+criteria);
    	
    	if(null == criteria ){
    		throw new PlatformUncheckException("getAppList 参数不能为NULL", null);
    	}
    	
    	int size = appMapper.getAppCount(criteria);
    	
    	criteria.setOffset(PageUtil.getPageOffset(criteria.getPageSize(), criteria.getCurrentPage()));
    	criteria.setTotalRows(size);
    	
    	List<App> lt = appMapper.getAppList(criteria);
    	
    	criteria.setList(lt);
        return criteria;
    }

    @Override
    public int getAppCount(AppCriteria criteria) {
    	int size = appMapper.getAppCount(criteria);
        return size;
    }

	@Override
	public App get(String appId) {
		log.debug("...get start appId ====>"+appId);
		
		if(StringUtils.isEmpty(appId)){
    		throw new PlatformUncheckException("get 参数(appId)不能为NULL", null);
    	}
		
		return appMapper.get(appId);
		
	}

}
