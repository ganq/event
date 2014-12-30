/**
 * Copyright mysoft Limited (c) 2014. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of mysoft Limited. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from mysoft or an authorized sublicensor.
 */
package com.mysoft.b2b.event.provider;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mysoft.b2b.commons.exception.PlatformUncheckException;
import com.mysoft.b2b.event.api.EventCenterService;
import com.mysoft.b2b.event.api.app.App;
import com.mysoft.b2b.event.api.app.AppSubscribe;
import com.mysoft.b2b.event.api.app.AppSubscribeService;
import com.mysoft.b2b.event.api.event.Event;
import com.mysoft.b2b.event.api.event.EventCriteria;
import com.mysoft.b2b.event.api.event.EventService;

/**
 * chengp: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * chengp    1.0           2014年8月27日     Created
 *
 * </pre>
 * @since b2b 2.0.0
 */
@Service("eventCenterService")
public class EventCenterServiceImpl implements EventCenterService {
	
	private static final Logger log = Logger.getLogger(EventCenterServiceImpl.class);
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private AppSubscribeService appSubscribeService;

    @Override
    public void saveEvent(Event event) {
    	log.debug("...save Event start ====>"+event);
    	if(event == null){
    		throw new PlatformUncheckException("saveEvent 参数 (Event) 不能为NULL", null);
    	}
    	
        eventService.insertEvent(event);
    }

    @Override
    public void saveSubscribe(App app, List<AppSubscribe> subscribes) {
    	log.debug("...saveSubscribe start ===> App"+app+" List<AppSubscribe> "+subscribes);
    	if(app == null || StringUtils.isEmpty(app.getAppId())){
    		throw new PlatformUncheckException("saveSubscribe 参数 (App) 不能为NULL或者appId不能为空", null);
    	}
    	if(subscribes == null){
    		log.debug("没有事件需要订阅");
    		return;
    	}
    	
    	for(AppSubscribe appSubscribe: subscribes){
    		if(StringUtils.isEmpty(appSubscribe.getAppId())){
    			appSubscribe.setAppId(app.getAppId());
    		}
    		appSubscribeService.insert(appSubscribe);
    	}
    }

}
