package com.shard.jdbc.config;

import io.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;

@Component
@Slf4j
public class FlywayInit {
    @Resource
    private ShardingDataSource dataSource;

    @Bean
    public void flyway2() {
        Map<String, DataSource> dataSourceMap = dataSource.getDataSourceMap();
        for (Map.Entry<String, DataSource> entry : dataSourceMap.entrySet()) {
            ClassicConfiguration configuration = new ClassicConfiguration();
            configuration.setBaselineOnMigrate(true);
            configuration.setDataSource(entry.getValue());
            configuration.setCleanOnValidationError(true);
            org.flywaydb.core.Flyway flyway = new org.flywaydb.core.Flyway(configuration);
            flyway.migrate();
        }

    }
}
