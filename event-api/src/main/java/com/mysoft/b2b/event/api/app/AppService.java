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
 * </pre>
 * @since b2b 2.0.0
 */

public interface AppService {
    
    /**
     * 
     * @param app
     */
    public App insert(App app);
    
    /**
     * 
     * @param app
     */
    public void update(App app);
    
    /**
     * 
     * @param appId
     */
    public void delete(String appId);
    
    /**
     * @param appId
     */
    public App get(String appId);
    
    /**
     * 
     * @param criteria
     * @return
     */
    public AppCriteria getAppList(AppCriteria criteria);
    
    /**
     * 
     * @param criteria
     * @return
     */
    public int getAppCount(AppCriteria criteria);
    
}
