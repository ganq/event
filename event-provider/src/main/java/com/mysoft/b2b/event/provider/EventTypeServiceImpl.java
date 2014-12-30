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
import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysoft.b2b.commons.exception.PlatformUncheckException;
import com.mysoft.b2b.event.api.event.EventType;
import com.mysoft.b2b.event.api.event.EventTypeCriteria;
import com.mysoft.b2b.event.api.event.EventTypeService;
import com.mysoft.b2b.event.mapper.EventTypeMapper;
import com.mysoft.b2b.event.util.PageUtil;

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
@Service("eventTypeService")
public class EventTypeServiceImpl implements EventTypeService {
	
	private static final Logger log = Logger.getLogger(EventTypeServiceImpl.class);
	
	@Autowired
	private EventTypeMapper eventTypeMapper;
	
    @Override
    public EventTypeCriteria getEventTypeList(EventTypeCriteria criteria) {
    	log.debug("...getEventTypeList start criteria ====>"+criteria);
        if (null == criteria) {
			throw new PlatformUncheckException("getEventTypeList 参数不能为NULL", null);
		}
        
        int size = eventTypeMapper.getEventTypeCount(criteria);
        criteria.setOffset(PageUtil.getPageOffset(criteria.getPageSize(),
				criteria.getCurrentPage()));
		criteria.setTotalRows(size);
		
		List<EventType> list = eventTypeMapper.getEventTypeList(criteria);
		criteria.setList(list);
		
		return criteria;
    }

}
