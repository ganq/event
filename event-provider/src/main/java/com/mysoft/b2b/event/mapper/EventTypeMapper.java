/**
 * Copyright mysoft Limited (c) 2014. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of mysoft Limited. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from mysoft or an authorized sublicensor.
 */
package com.mysoft.b2b.event.mapper;

import java.util.List;

import com.mysoft.b2b.event.api.event.EventType;
import com.mysoft.b2b.event.api.event.EventTypeCriteria;

/**
 * Administrator: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * Administrator    1.0           2014年8月26日     Created
 *
 * </pre>
 * @since b2b 1.5.0
 */

public interface EventTypeMapper {
	
	public List<EventType> getEventTypeList(EventTypeCriteria criteria);
	
	public int getEventTypeCount(EventTypeCriteria criteria);

}
