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
import com.mysoft.b2b.event.api.app.AppSubscribe;
import com.mysoft.b2b.event.api.app.AppSubscribeCriteria;
import com.mysoft.b2b.event.api.app.AppSubscribeService;
import com.mysoft.b2b.event.mapper.AppSubscribeMapper;
import com.mysoft.b2b.event.util.PageUtil;

/**
 * chengp: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * chengp    1.0           2014年8月27日     Created
 * </pre>
 * @since b2b 2.0.0
 */
@Service("appSubscribeService")
public class AppSubscribeServiceImpl implements AppSubscribeService {
	
	private static final Logger log = Logger.getLogger(AppSubscribeServiceImpl.class);
	
	@Autowired
	private IdGenerationService idGenerationService;
	
	@Autowired
	private AppSubscribeMapper appSubscribeMapper;
	
	
    @Override
    public void insert(AppSubscribe appSubscribe) {
    	log.debug("...insert  start appSubscribe ====>"+appSubscribe);
        if(null == appSubscribe 
    			|| StringUtils.isEmpty(appSubscribe.getAppId())
    			){
    		throw new PlatformUncheckException("insert 参数(,,,getAppId)不能为NULL", null);
    		}
        long id = idGenerationService.getNextId("b2b_support.bizp_event_app_subscribe");

        appSubscribe.setSubscribeId(String.valueOf(id));
        appSubscribeMapper.insert(appSubscribe);
    }

    @Override
    public void update(AppSubscribe appSubscribe) {
    	log.debug("...update  start appSubscribe ====>"+appSubscribe);
        if(null == appSubscribe 
    			|| StringUtils.isEmpty(appSubscribe.getAppId())
    			|| StringUtils.isEmpty(appSubscribe.getSubscribeId())
    			){
    		throw new PlatformUncheckException("update 参数(getSubscribeId,,,getAppId)不能为NULL", null);
    		}
        appSubscribeMapper.update(appSubscribe);
    }

    @Override
    public void delete(String appSubscribeId) {
    	log.debug("...delete  start appSubscribeId ====>"+appSubscribeId);
        if(StringUtils.isEmpty(appSubscribeId)){
    		throw new PlatformUncheckException("delete 参数(appSubscribeId)不能为NULL", null);
    	}
        appSubscribeMapper.delete(appSubscribeId);  
    }

    @Override
    public AppSubscribeCriteria getAppSubscribeList(AppSubscribeCriteria criteria) {
    	log.debug("...getAppSubscribeList start criteria ====>"+criteria);
    	
    	if(null == criteria ){
    		throw new PlatformUncheckException("getAppSubscribeList 参数不能为NULL", null);
    	}
    	
    	int size = appSubscribeMapper.getAppSubscribeCount(criteria);
    	
    	criteria.setOffset(PageUtil.getPageOffset(criteria.getPageSize(), criteria.getCurrentPage()));
    	criteria.setTotalRows(size);
    	
    	List<AppSubscribe> lt = appSubscribeMapper.getAppSubscribeList(criteria);
    	
    	criteria.setList(lt);
        return criteria;
    }
    	
    @Override
    public int getAppSubscribeCount(AppSubscribeCriteria criteria) {
    	int size = appSubscribeMapper.getAppSubscribeCount(criteria);
        return size;
    }

}
