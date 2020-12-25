package com.scaffold.simple.admin.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * @Author: tianjingle
 * @Date: 2019/7/4 15:40
 * @Discption: liquibase配置
 */

@Configuration
public class LiquibaseConfig {

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) throws Exception {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setShouldRun(false);
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/changelog-master.xml");
        liquibase.setContexts("development,test,production");
        System.out.println("liquibaseConfig初始化");
        return liquibase;
    }
}
