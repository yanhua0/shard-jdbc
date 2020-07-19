# 项目内容
springboot整合数据库中间件shard-jdbc+mysql+pgsql
# shard-jdbc官网介绍
官网链接:https://shardingsphere.apache.org/ 
Apache ShardingSphere是一个开源生态系统，由一组分布式数据库中间件解决方案组成，其中包括JDBC，代理和Sidecar（计划）这3种独立产品。它们都提供数据分片，分布式事务和数据库编排功能，适用于各种情况，例如Java同构，异构语言和云原生。

为了合理地利用分布式系统中数据库的计算和存储容量，ShardingSphere将自己定义为中间件，而不是全新的数据库类型。作为许多企业的基石，关系数据库仍然占据着巨大的市场份额。因此，在当前阶段，我们宁愿关注其增量而不是总的倾覆。

Apache ShardingSphere从5.x版本开始专注于可插拔架构，其功能可以嵌入到项目灵活性中。当前，插件支持了诸如数据分片，读写拆分，多副本，数据加密，影子测试以及支持SQL方言/数据库协议（如MySQL，PostgreSQL，SQLServer，Oracle）的功能。开发人员可以像构建乐高积木一样自定义自己的ShardingSphere。现在，Apache ShardingSphere有许多SPI扩展，并且会不断增加。

ShardingSphere于2020年4月16日成为Apache顶级项目。
# 项目数据库名称命名
shard_0,shard_1,shard_2,shard_3....
# pgsql问题
聚合函数查询: select count(*) AS num fron table_one_0;语句必须要使用别名。 
官网介绍如下:
使用SQLSever和PostgreSQL时，聚合列不加别名会抛异常？
回答：

SQLServer和PostgreSQL获取不加别名的聚合列会改名。例如，如下SQL：

SELECT SUM(num), SUM(num2) FROM tablexxx;
SQLServer获取到的列为空字符串和(2)，PostgreSQL获取到的列为空sum和sum(2)。这将导致ShardingSphere在结果归并时无法找到相应的列而出错。

正确的SQL写法应为：

SELECT SUM(num) AS sum_num, SUM(num2) AS sum_num2 FROM tablexxx;

