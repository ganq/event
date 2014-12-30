/**
 * Copyright mysoft Limited (c) 2014. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of mysoft Limited. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from mysoft or an authorized sublicensor.
 */
package com.mysoft.b2b.event.api;

import java.util.List;

import com.mysoft.b2b.event.api.app.App;
import com.mysoft.b2b.event.api.app.AppSubscribe;
import com.mysoft.b2b.event.api.event.Event;

/**
 * Administrator: 异步事件模型对外接口
 * @version   Revision History
 * <pre>
 * Author           Version       Date        Changes
 * Administrator    1.0           2014年8月25日     Created
 * 事件中心服务类
 * </pre>
 * @since b2b 1.5.0
 */

public interface EventCenterService {

    /**
     * 客户端发布事件到事件中心 
     * @param event
     */
    public void saveEvent(Event event);

    /**
     * 客户端发布订阅到事件中心
     * @param app
     * @param subscribes
     */
    public void saveSubscribe(App app, List<AppSubscribe> subscribes);

}
