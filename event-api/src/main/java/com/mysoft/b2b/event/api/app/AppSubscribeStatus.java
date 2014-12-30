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
 * chengp    1.0           2014年8月28日     Created
 * hedx      1.0           2014年9月11日     Enhancement
 * </pre>
 * @since b2b 2.0.0
 */

public enum AppSubscribeStatus {
    
    Need_Compatibility(1), NO_Compatibility(2), Steady(3), NO_Use(4), Invalid(5);

    private AppSubscribeStatus(int value) {
        this.value = value;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
}
