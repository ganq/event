/**
 * Copyright mysoft Limited (c) 2014. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of mysoft Limited. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from mysoft or an authorized sublicensor.
 */
package com.mysoft.b2b.event.scheduler.protocol.rmi;

import org.apache.log4j.Logger;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import com.mysoft.b2b.event.api.ProtocolService;

/**
 * chengp:    RMI客户端
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * chengp    1.0           2014年8月28日     Created
 *
 * </pre>
 * @since b2b 2.0.0
 */

public class RMIClientHelper {

    private static Logger logger = Logger.getLogger(RMIClientHelper.class);

    public static ProtocolService getRMIProtocolService(String protocolUrl) {
        String serviceUrl = "";
        if (protocolUrl != null && protocolUrl.endsWith("/")) {
            serviceUrl = protocolUrl;
        } else {
            serviceUrl = protocolUrl + "/";
        }
        serviceUrl = serviceUrl + ProtocolService.class.getSimpleName();
        RmiProxyFactoryBean rpfb = new RmiProxyFactoryBean();
        rpfb.setServiceUrl(serviceUrl);
        rpfb.setServiceInterface(ProtocolService.class);
        try {
            rpfb.setLookupStubOnStartup(false);
            rpfb.setRefreshStubOnConnectFailure(true);
            rpfb.afterPropertiesSet();
        } catch (Exception e) {
            logger.info("Can't get the remote RMI server: " + serviceUrl + ", because " + e.getMessage());
            return null;
        }
        logger.info("Success to init RMI local proxy service for " + serviceUrl);
        return (ProtocolService) rpfb.getObject();
    }

}
