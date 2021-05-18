//package com.scaffold.simple.admin.other.zookeeper;
//
//import org.apache.zookeeper.WatchedEvent;
//import org.apache.zookeeper.Watcher;
//
//import java.util.concurrent.CountDownLatch;
//
///**
// * @Author: tianjl
// * @Date: 2021/1/19 15:05
// * @Eamil: 2695062879@qq.com
// */
//public class MyZkWatcher implements Watcher {
//
//    private CountDownLatch countDownLatch;
//
//    private String tianjingle;
//
//    public MyZkWatcher(String info){
////        this.countDownLatch=countDownLatch;
//        this.tianjingle=info;
//    }
//
//    @Override
//    public void process(WatchedEvent watchedEvent) {
////        this.countDownLatch.countDown();
//        System.out.println("water 被触发了");
//    }
//}
