package com.shard.jdbc.controller;

import com.shard.jdbc.service.ShardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    private ShardService shardService;

    @GetMapping("/fin")
    public Integer tt() {
       return shardService.F();
    }
}
