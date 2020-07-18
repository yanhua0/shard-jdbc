package com.shard.jdbc.config;

import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.core.keygen.DefaultKeyGenerator;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
@Configuration
public class ShardingDataSourceConfig {
    @Resource
    private ShardJdbcCon shardJdbcCon;
    /**
     * Shard-JDBC 分库配置
     */
    @Bean
    public DataSource dataSource () throws Exception {
        ShardingRuleConfiguration shardJdbcConfig = new ShardingRuleConfiguration();
        shardJdbcConfig.getTableRuleConfigs().add(getTableRule01());
        // shardJdbcConfig.getTableRuleConfigs().add(getTableRule02());
        shardJdbcConfig.setDefaultDataSourceName("ds_0");
        Properties prop = new Properties();
        prop.setProperty("sql.show","true");
        prop.put("executor.size","100");
        Map<String,DataSource> dataMap = new LinkedHashMap<>() ;
        String prefix="ds_";
        String data="shard_";
        String url="jdbc:postgresql://localhost:5432/%s";
        for (int i = 0; i <DataSourceConstant.DATABASE_COUNT; i++) {
            String s=String.format(url,data+i);
            dataMap.put(prefix+i,shardJdbcCon.dataOneSource(s));
        }
        return ShardingDataSourceFactory.createDataSource(dataMap, shardJdbcConfig, new HashMap<>(), prop);
    }

    /**
     * Shard-JDBC 分表配置
     */
    private static TableRuleConfiguration getTableRule01() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("table_one");
        result.setActualDataNodes(DataSourceConstant.get());
        DefaultKeyGenerator defaultKeyGenerator=new DefaultKeyGenerator();
        result.setKeyGenerator(defaultKeyGenerator);
        result.setKeyGeneratorColumnName("id");
        result.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("phone", new DataSourceAlg()));
        result.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("phone", new TableOneAlg()));
        return result;
    }
//    private static TableRuleConfiguration getTableRule02() {
//        TableRuleConfiguration result = new TableRuleConfiguration();
//        result.setLogicTable("table_two");
//        result.setActualDataNodes(DataSourceConstant.get());
//        result.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("phone", new DataSourceAlg()));
//        result.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("phone", new TableTwoAlg()));
//        return result;
//    }
}
