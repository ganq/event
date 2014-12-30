/**
 * Copyright mysoft Limited (c) 2014. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of mysoft Limited. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from mysoft or an authorized sublicensor.
 */
package com.mysoft.b2b.event.api.event;

/**
 * chengp: 事件状态
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * chengp    1.0           2014年8月27日     Created
 *
 * </pre>
 * @since b2b 2.0.0
 */

public enum EventLogStatus {

    DEFAULT(1), DEALING(2), SUCCESS(3), FAILED(4), STOPED(5);

    private EventLogStatus(int value) {
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
