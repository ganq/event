/**
 * Copyright mysoft Limited (c) 2014. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of mysoft Limited. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from mysoft or an authorized sublicensor.
 */
package com.mysoft.b2b.event.api.protocol.rmi;

import java.rmi.RemoteException;

import org.springframework.remoting.rmi.RmiServiceExporter;

import com.mysoft.b2b.event.api.ProtocolService;

/**
 * chengp:    RMI服务器端，屏蔽Spring
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * chengp    1.0           2014年8月28日     Created
 *
 * </pre>
 * @since b2b 2.0.0
 */

public class RMIServer extends RmiServiceExporter{
    
    /**
     * 
     */
    private ProtocolService service;
    /**
     * 
     */
    private String host;
    /**
     * 
     */
    private int port;
    
    @Override
    public void afterPropertiesSet() throws RemoteException {
        
        this.setServiceName(ProtocolService.class.getSimpleName());
        this.setServiceInterface(ProtocolService.class);
        this.setService(service);
        this.setRegistryHost(host);
        this.setRegistryPort(port);
        
        super.afterPropertiesSet();
    }

    public void setService(ProtocolService service) {
        this.service = service;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
}
