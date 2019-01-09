package com.lori.zookeeperdemo.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

public class ZookeeperClient implements InitializingBean,DisposableBean {

    @Value("${zookeeper.server}")
    private String zookeeperServer;

    private CuratorFramework curatorFramework;

    public CuratorFramework getCuratorFramework(){
        return curatorFramework;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (null == curatorFramework){
            curatorFramework = CuratorFrameworkFactory.newClient(zookeeperServer, (i, l, retrySleeper)-> false);
            curatorFramework.start();
        }
    }

    @Override
    public void destroy() throws Exception {
        if (null != curatorFramework){
            curatorFramework.close();
        }
    }
}