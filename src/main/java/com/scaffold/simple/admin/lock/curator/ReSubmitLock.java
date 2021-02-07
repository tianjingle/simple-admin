package com.scaffold.simple.admin.lock.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

/**
 * @Author: tianjl
 * @Date: 2021/2/2 8:55
 * @Eamil: 2695062879@qq.com
 */
@Component
public class ReSubmitLock {


    /**
     * 进行一些操作
     */
    @Autowired
    private CuratorFramework client;

    /**
     * 放重复提交
     * @param key
     * @param token
     * @return
     * @throws Exception
     */
    public boolean check(String key, String token) throws Exception {
        boolean status = false;
        String mainKey="/"+key;
        InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(client, mainKey);
//        InterProcessLock interProcessLock = interProcessReadWriteLock.writeLock();
        InterProcessLock interProcessLock = interProcessReadWriteLock.readLock();
        System.out.println("等待获取锁对象!");
        // 获取锁
        try {
            interProcessLock.acquire();
            // 读取数据时读取节点的属性
            Stat stat = new Stat();
            byte[] zkToken = client.getData()
                    .storingStatIn(stat)
                    .forPath(mainKey);
            String oldToken = new String(zkToken);
            System.out.println("允许的Token：" + oldToken);
            if (token.equals(oldToken)) {
                System.out.println("校验成功！");
                generateToken(key);
                status = true;
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("节点加锁产生错误");
        }finally {
            // 释放锁
            interProcessLock.release();
            System.out.println("等待释放锁!");
        }
        if (!status) {
            System.out.println("不能重复提交表单");
        }
        return status;
    }

    /**
     * 创建token
     */
    public String generateToken(String key) throws Exception {
        String mainKey="/"+key;
        String newToken = UUID.randomUUID().toString();
        System.out.println("设置的新token为：" + newToken);
        // 判断节点是否存在,为null表示不存在
        Stat stat= client.checkExists()
                // 节点路径
                .forPath(mainKey);
        if (!Objects.isNull(stat)){
            client.setData()
                    .forPath(mainKey, newToken.getBytes());
        }else{
            client.create()
                    .forPath(mainKey, newToken.getBytes());
        }
        return newToken;
    }
}
