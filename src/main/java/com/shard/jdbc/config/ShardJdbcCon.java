package com.shard.jdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 数据库分库分表配置
 */
@Component
public class ShardJdbcCon {

    @Value("${spring.datasource.dataOne.druid.url}")
    private String dbUrl1;
    @Value("${spring.datasource.dataOne.druid.username}")
    private String username1;
    @Value("${spring.datasource.dataOne.druid.password}")
    private String password1;
    @Value("${spring.datasource.dataOne.druid.driverClassName}")
    private String driverClassName1;
    @Value("${spring.datasource.dataOne.druid.initial-size}")
    private int initialSize1;
    @Value("${spring.datasource.dataOne.druid.max-active}")
    private int maxActive1;
    @Value("${spring.datasource.dataOne.druid.min-idle}")
    private int minIdle1;
    @Value("${spring.datasource.dataOne.druid.max-wait}")
    private int maxWait1;
    @Value("${spring.datasource.dataOne.druid.pool-prepared-statements}")
    private boolean poolPreparedStatements1;
    @Value("${spring.datasource.dataOne.druid.max-pool-prepared-statement-per-connection-size}")
    private int maxPoolPreparedStatementPerConnectionSize1;
    @Value("${spring.datasource.dataOne.druid.time-between-eviction-runs-millis}")
    private int timeBetweenEvictionRunsMillis1;
    @Value("${spring.datasource.dataOne.druid.min-evictable-idle-time-millis}")
    private int minEvictableIdleTimeMillis1;
    @Value("${spring.datasource.dataOne.druid.max-evictable-idle-time-millis}")
    private int maxEvictableIdleTimeMillis1;
    @Value("${spring.datasource.dataOne.druid.validation-query}")
    private String validationQuery1;
    @Value("${spring.datasource.dataOne.druid.test-while-idle}")
    private boolean testWhileIdle1;
    @Value("${spring.datasource.dataOne.druid.test-on-borrow}")
    private boolean testOnBorrow1;
    @Value("${spring.datasource.dataOne.druid.test-on-return}")
    private boolean testOnReturn1;
    @Value("{spring.datasource.dataOne.druid.connection-properties}")
    private String connectionProperties1;

    public DruidDataSource dataOneSource(String url) {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setUsername(username1);
        datasource.setPassword(password1);
        datasource.setDriverClassName(driverClassName1);
        datasource.setInitialSize(initialSize1);
        datasource.setMinIdle(minIdle1);
        datasource.setMaxActive(maxActive1);
        datasource.setMaxWait(maxWait1);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis1);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis1);
        datasource.setMaxEvictableIdleTimeMillis(minEvictableIdleTimeMillis1);
        datasource.setValidationQuery(validationQuery1);
        datasource.setTestWhileIdle(testWhileIdle1);
        datasource.setTestOnBorrow(testOnBorrow1);
        datasource.setTestOnReturn(testOnReturn1);
        datasource.setPoolPreparedStatements(poolPreparedStatements1);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize1);
        datasource.setConnectionProperties(connectionProperties1);
        return datasource;
    }

}
