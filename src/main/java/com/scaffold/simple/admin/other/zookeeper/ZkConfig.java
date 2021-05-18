//package com.scaffold.simple.admin.other.zookeeper;
//
//import org.apache.zookeeper.ZooKeeper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//import java.util.concurrent.CountDownLatch;
//
///**
// * @Author: tianjl
// * @Date: 2021/1/19 16:00
// * @Eamil: 2695062879@qq.com
// */
//@Configuration
//public class ZkConfig {
//
//    @Bean
//    public ZooKeeper create() throws IOException {
////        CountDownLatch countDownLatch = new CountDownLatch(1);
//        System.out.println("prepare...");
//        ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 3000,myZkWatcher() );
//        System.out.println("complate connect..");
//        return zk;
//    }
//
//    @Bean
//    public MyZkWatcher myZkWatcher(){
//        return new MyZkWatcher("123");
//    }
//}
