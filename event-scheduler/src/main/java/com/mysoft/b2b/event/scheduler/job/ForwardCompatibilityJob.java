/**
 * Copyright mysoft Limited (c) 2014. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of mysoft Limited. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from mysoft or an authorized sublicensor.
 */
package com.mysoft.b2b.event.scheduler.job;

import com.mysoft.b2b.commons.scheduler.MysoftJob;


/**
 * chengp:    遍历数据，向前兼容（需要向前兼容的业务-----因停止服务等原因造成的支持）
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * chengp     1.0           2014年8月28日    Created
 * </pre>
 * @since b2b 2.0.0
 */

public class ForwardCompatibilityJob extends MysoftJob {

    @Override
    public void run() {
        
    }

}
