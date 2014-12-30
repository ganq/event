package com.mysoft.b2b.event;

import org.apache.log4j.Logger;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "classpath*:/META-INF/spring/*.xml" })
public class BaseTestCase {

    protected Logger logger = Logger.getLogger(this.getClass());
    
    public static void main(String[] args) {
		com.alibaba.dubbo.container.Main.main(args);
	}
}

