package com.scaffold.simple.admin.lock.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
     * @param userId
     * @param token
     * @return
     * @throws Exception
     */
    public boolean check(String userId, String token) throws Exception {
        boolean status = false;
        String mainKey="/"+userId;
        InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(client, mainKey);
        InterProcessLock interProcessLock = interProcessReadWriteLock.writeLock();
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
                generateToken(userId);
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
    public String generateToken(String userId) throws Exception {
        String mainKey="/"+userId;
        String newToken = UUID.randomUUID().toString();
        System.out.println("设置的新token为：" + newToken);
        client.setData()
                .forPath(mainKey, newToken.getBytes());
        return newToken;
    }


}
