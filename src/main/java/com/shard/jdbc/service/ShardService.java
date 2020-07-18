package com.shard.jdbc.service;

import com.shard.jdbc.entity.TableOne;

public interface ShardService {
    void createTable() ;
    void insertOne() throws InterruptedException;
    void insertTwo() ;
    TableOne selectOneByPhone(String phone) ;
    int F();
}
