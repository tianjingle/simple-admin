package com.scaffold.simple.admin.config;

import org.apache.log4j.LogManager;
import org.apache.logging.log4j.core.impl.Log4jContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;
import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;

/**
 * @Author: tianjl
 * @Date: 2020/9/1 10:00
 * @Eamil: 2695062879@qq.com
 */

//TODO 这里下期要做
public class SimpleAdminConfig {

    public static Logger logger= LoggerFactory.getLogger("123");
    //*masfdl
    public static org.apache.log4j.Logger loggerTwo=LogManager.getLogger("");
//
    Log4jContextFactory tt;
    Log4J2LoggingSystem rr;
    Log4jLoggerAdapter log4jloggerAdapter;
    public static void main(String[] args) {
        logger.debug("123");
    }
}
