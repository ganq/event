/**
 * Copyright mysoft Limited (c) 2014. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of mysoft Limited. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from mysoft or an authorized sublicensor.
 */
package com.mysoft.b2b.event.api.app;



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

public interface AppSubscribeService {

    /**
     * 
     * @param appSubscribe
     */
    public void insert(AppSubscribe appSubscribe);
    
    /**
     * 
     * @param appSubscribe
     */
    public void update(AppSubscribe appSubscribe);
    
    /**
     * 
     * @param appSubscribeId
     */
    public void delete(String appSubscribeId);
    
    /**
     * 
     * @param criteria
     * @return
     */
    public AppSubscribeCriteria getAppSubscribeList(AppSubscribeCriteria criteria);
    
    /**
     * 
     * @param criteria
     * @return
     */
    public int getAppSubscribeCount(AppSubscribeCriteria criteria);
    
}
