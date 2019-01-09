package com.lori.zookeeperdemo.controller;

import com.lori.zookeeperdemo.config.ZookeeperClient;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zookeeper")
public class ZookeeperController {

    private static final String ZOOKEEPER_PATH = "/lori_test_zookeeper_key";

    @Autowired
    private ZookeeperClient zookeeperClient;

    @RequestMapping(value = "/insert")
    public String insertZookeeperValue(@RequestParam(value = "zkValue") String zkValue) throws Exception {
        Stat stat = zookeeperClient.getCuratorFramework().checkExists().forPath(ZOOKEEPER_PATH);
        if (null == stat){
            zookeeperClient.getCuratorFramework().create().forPath(ZOOKEEPER_PATH,zkValue.getBytes());
        }else {
            zookeeperClient.getCuratorFramework().setData().forPath(ZOOKEEPER_PATH,zkValue.getBytes());
        }
        return "insert success";
    }

    @RequestMapping(value = "/get")
    public String getZookeeperValue() throws Exception {
        return new String(zookeeperClient.getCuratorFramework().getData().forPath(ZOOKEEPER_PATH));
    }

    @RequestMapping(value = "/delete")
    public String deleteZookeeperValue() throws Exception {
        zookeeperClient.getCuratorFramework().delete().forPath(ZOOKEEPER_PATH);
        return "delete success";
    }
}