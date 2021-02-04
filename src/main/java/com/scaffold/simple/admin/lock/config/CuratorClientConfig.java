package com.scaffold.simple.admin.lock.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.retry.RetryUntilElapsed;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: tianjl
 * @Date: 2021/2/1 13:25
 * @Eamil: 2695062879@qq.com
 */
@Configuration
public class CuratorClientConfig {


    @Bean
    public CuratorFramework main() {
        //3秒后重连一次，只重连1次
        RetryPolicy retryPolicy0 = new RetryOneTime(3000);

        RetryPolicy retryPolicy1 = new RetryNTimes(3, 3000);
        //            每3秒重连一次，总等待时间超过10秒后停止重连
        RetryPolicy retryPolicy2 = new RetryUntilElapsed(10000, 3000);
        //         baseSleepTimeMs * Math.max(1, random.nextInt(1 << (retryCount + 1)))
        //        每3秒重连一次，重连3次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //创建连接对象
        CuratorFramework client = CuratorFrameworkFactory.builder()
                //IP地址端口号
                .connectString("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183")
                //客户端与服务器之间的会话超时时间
                .sessionTimeoutMs(10000)
                //当客户端与服务器之间会话超时3s后，进行一次重连
                .retryPolicy(retryPolicy)
                //命名空间，当我们创建节点的时候，以/create为父节点
                .namespace("create")
                //构建连接对象
                .build();
        //打开连接
        client.start();
        //是否成功建立连接，true ：建立， false：没有建立
        System.out.println(client.isStarted());
        return client;
    }
}
