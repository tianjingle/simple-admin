package com.scaffold.simple.admin.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @Author: tianjl
 * @Date: 2021/1/29 16:04
 * @Eamil: 2695062879@qq.com
 */
@Component
@Slf4j
public class LockTest implements ApplicationContextAware {

    static ZooKeeper zooKeeper;


    public boolean check(String userId,String token) throws KeeperException, InterruptedException {
        userId="/"+userId;
        if (!Objects.isNull(zooKeeper)){
            Stat s=zooKeeper.exists(userId,true);
            if (!Objects.isNull(s)){
                String oldToken=new String(zooKeeper.getData(userId,false,s));
                if (oldToken.equals(token)){
                    System.out.println("第一次提交的，暗号对上了");
                    setNewToken(userId,s);
                    return true;
                }
            }
            return false;
        }else {
            log.error("zookeeper intited fails");
            return false;
        }
    }


    public boolean setNewToken(String userId,Stat s) throws KeeperException, InterruptedException {
        String newToken= UUID.randomUUID().toString();
        Stat newStat = zooKeeper.setData(userId,newToken.getBytes(), s.getVersion());
        if (!Objects.isNull(newStat)){
            System.out.println(newToken);
            return true;
        }
        return false;
    }

    public boolean realse(String token) throws KeeperException, InterruptedException {
        token="/"+token;
        if (!Objects.isNull(zooKeeper)){
            Stat s=zooKeeper.exists(token,true);
            if (null!=s){
                System.out.println("准备释放");
                zooKeeper.delete(token,s.getVersion());
                return false;
            }else{
                return true;
            }
        }else{
            System.out.println("没有初始化");
            return false;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.zooKeeper=applicationContext.getBean(ZooKeeper.class);
    }

    /**
     * 创建token
     * @param userId
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void generate(String userId) throws KeeperException, InterruptedException {
        userId="/"+userId;
        Stat s=zooKeeper.exists(userId,true);
        if (!Objects.isNull(s)){
            setNewToken(userId,s);
        }else{
            List<ACL> acl = ZooDefs.Ids.OPEN_ACL_UNSAFE;
            CreateMode createMode = CreateMode.PERSISTENT;
            String uid=UUID.randomUUID().toString();
            System.out.println(uid);
            String result = zooKeeper.create(userId, uid.getBytes(), acl, createMode);
            //创建节点有两种，上面是第一种，还有一种可以使用回调函数及参数传递，与上面方法名称相同。
            System.out.println(MessageFormat.format("创建节点返回结果：{0}",result));
        }
    }
}
