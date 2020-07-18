package com.shard.jdbc.service.impl;


import com.shard.jdbc.entity.TableOne;
import com.shard.jdbc.mapper.TableOneMapper;
import com.shard.jdbc.service.ShardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.*;

@Service
@Slf4j
public class ShardServiceImpl implements ShardService {

    @Resource
    private TableOneMapper tableOneMapper;

    @Override
    public void createTable() {

    }

    @Override
    //@Transactional
    //@ShardingTransactionType(TransactionType.XA)
    public void insertOne() throws InterruptedException {
        int cc=500000000-tableOneMapper.selectCount();
        ExecutorService e = Executors.newSingleThreadExecutor();
        e.execute(() -> {
            ExecutorService executorService = new ThreadPoolExecutor(100, 100,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());

            for (int i = 0; i < cc; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                int finalI = i;
                executorService.execute(() -> {
                    try {
                        TableOne tableOne = new TableOne();
                        tableOne.setPhone("phone" + finalI);
                        tableOne.setBackOne("back_one" + finalI);
                        tableOne.setBackTwo("back_two" + finalI);
                        tableOne.setBackThree("back_three" + finalI);
                        long ss = System.currentTimeMillis();
                        tableOneMapper.insert(tableOne);
                        log.info("添加数据花费时间{}ms", System.currentTimeMillis() - ss);
                    } catch (Exception e1) {
                        System.out.println(e1);
                    }
                });

            }
        });


    }

    @Override
    public void insertTwo() {

    }


    @Override
    public TableOne selectOneByPhone(String phone) {
        return tableOneMapper.selectOneByPhone(phone);
    }


    @Override
    public int F() {
        return tableOneMapper.selectCount();
    }
}
