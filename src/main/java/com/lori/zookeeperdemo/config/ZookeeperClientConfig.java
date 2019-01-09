package com.lori.zookeeperdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZookeeperClientConfig {

    @Bean
    public ZookeeperClient zookeeperClient(){
        return new ZookeeperClient();
    }
}