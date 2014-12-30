/**
 * Copyright mysoft Limited (c) 2014. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of mysoft Limited. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from mysoft or an authorized sublicensor.
 */
package com.mysoft.b2b.event.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.mysoft.b2b.event.api.event.Event;

/**
 * chengp:    数据交换接口(各个业务需要实现的接口)
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * chengp     1.0           2014年8月28日     Created
 *
 * </pre>
 * @since b2b 2.0.0
 */

public interface ProtocolService extends Remote{
    
    /**
     * 单个处理事件
     * @param event
     * @return
     */
    public boolean dealEvent(Event event) throws RemoteException;
    
    /**
     * 批量处理(新的订阅,服务器暂停等情况，向前兼容模式)
     * @param eventList
     * @return
     */
    public boolean dealEvent(List<Event> eventList) throws RemoteException;
    
}
