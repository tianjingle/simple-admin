package com.scaffold.simple.admin.lock.curator;

import com.scaffold.simple.admin.utils.ResponseResult;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tianjl
 * @Date: 2021/2/1 13:40
 * @Eamil: 2695062879@qq.com
 */
@RestController
@RequestMapping(value = "/curator")
public class CuratorController {

    /**
     *进行一些操作
     */
    @Autowired
    private CuratorFramework client;


    @GetMapping(value = "/add")
    public ResponseResult get() throws Exception {
        client.create()
                // 节点的类型 持久化节点
                .withMode(CreateMode.PERSISTENT)
                // 节点的权限列表 world:anyone:cdrwa
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                // arg1:节点的路径
                // arg2:节点的数据
                .forPath("/node1", "hello".getBytes());
        System.out.println("结束");



        // 自定义权限列表
        // 权限列表
        List<ACL> list = new ArrayList<ACL>();
        // 授权模式和授权对象
        Id id = new Id("ip", "192.168.188.134");
        list.add(new ACL(ZooDefs.Perms.ALL, id));
        client.create().withMode(CreateMode.PERSISTENT).withACL(list).forPath("/node2", "node2".getBytes());
        System.out.println("结束");


        // 递归创建节点树
        //在创建/node3/node31的时候，node3是不存在，
        //我们可以通过递归创建node3节点
        //creatingParentsIfNeeded()方法进行递归创建
        client.create()
                // 递归节点的创建
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath("/node3/node31", "node31".getBytes());
        System.out.println("结束");


        // 异步方式创建节点
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                // 异步回调接口
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        // 节点的路径
                        System.out.println(curatorEvent.getPath());
                        // 时间类型
                        System.out.println(curatorEvent.getType());
                    }
                })
                .forPath("/node4","node4".getBytes());
        Thread.sleep(5000);
        System.out.println("结束");

        return ResponseResult.success("123");

    }


    @GetMapping(value = "/modify")
    public ResponseResult modify() throws Exception {
        // 更新节点
        client.setData()
                // arg1:节点的路径
                // arg2:节点的数据
                .forPath("/node1", "node11".getBytes());
        System.out.println("结束");


        client.setData()
                // 指定版本号
                .withVersion(1)
                .forPath("/node1", "node1111".getBytes());
        System.out.println("结束");

        // 异步方式修改节点数据
        client.setData()
                .withVersion(-1).inBackground(new BackgroundCallback() {
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                // 节点的路径
                System.out.println(curatorEvent.getPath());
                // 事件的类型
                System.out.println(curatorEvent.getType());
            }
        }).forPath("/node1", "node1".getBytes());
        Thread.sleep(5000);
        System.out.println("结束");
        return ResponseResult.success("123");
    }


    @GetMapping(value = "/delete")
    public ResponseResult delete() throws Exception {
        // 删除节点
        client.delete()
                // 节点的路径
                .forPath("/node1");
        System.out.println("结束");


        client.delete()
                // 版本号
                .withVersion(0)
                .forPath("/node1");
        System.out.println("结束");


        //删除包含字节点的节点
        client.delete()
                .deletingChildrenIfNeeded()
                .withVersion(-1)
                .forPath("/node1");
        System.out.println("结束");



        // 异步方式删除节点
        client.delete()
                .deletingChildrenIfNeeded()
                .withVersion(-1)
                .inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        // 节点路径
                        System.out.println(curatorEvent.getPath());
                        // 事件类型
                        System.out.println(curatorEvent.getType());
                    }
                })
                .forPath("/node1");
        Thread.sleep(5000);
        System.out.println("结束");

        return ResponseResult.success("123");

    }


    @GetMapping(value = "/find")
    public ResponseResult find() throws Exception {
        // 读取节点数据
        byte [] bys=client.getData()
                // 节点的路径
                .forPath("/node1");
        System.out.println(new String(bys));


        // 读取数据时读取节点的属性
        Stat stat=new Stat();
        byte [] bys1=client.getData()
                // 读取属性
                .storingStatIn(stat)
                .forPath("/node1");
        System.out.println(new String(bys1));
        System.out.println(stat.getVersion());


        // 异步方式读取节点的数据
        client.getData()
                .inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        // 节点的路径
                        System.out.println(curatorEvent.getPath());
                        // 事件类型
                        System.out.println(curatorEvent.getType());
                        // 数据
                        System.out.println(new String(curatorEvent.getData()));
                    }
                })
                .forPath("/node1");
        Thread.sleep(5000);
        System.out.println("结束");

     return ResponseResult.success("123");

    }

    @GetMapping(value = "/child")
    public ResponseResult child() throws Exception {
        // 读取子节点数据
        List<String> list = client.getChildren()
                // 节点路径
                .forPath("/get");
        for (String str : list) {
            System.out.println(str);
        }

        // 异步方式读取子节点数据
        client.getChildren()
                .inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        // 节点路径
                        System.out.println(curatorEvent.getPath());
                        // 事件类型
                        System.out.println(curatorEvent.getType());
                        // 读取子节点数据
                        List<String> list=curatorEvent.getChildren();
                        for (String str : list) {
                            System.out.println(str);
                        }
                    }
                })
                .forPath("/get");
        Thread.sleep(5000);
        System.out.println("结束");
        return ResponseResult.success(123);
    }

    @GetMapping(value = "eisit")
    public ResponseResult exixt() throws Exception {
        // 判断节点是否存在,为null表示不存在
        Stat stat= client.checkExists()
                // 节点路径
                .forPath("/kkkkkkkkkk");
        System.out.println(stat.getVersion());


        // 异步方式判断节点是否存在
        client.checkExists()
                .inBackground(new BackgroundCallback() {
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        // 节点路径
                        System.out.println(curatorEvent.getPath());
                        // 事件类型
                        System.out.println(curatorEvent.getType());
                        System.out.println(curatorEvent.getStat().getVersion());
                    }
                })
                .forPath("/node1");
        Thread.sleep(5000);
        System.out.println("结束");

        return ResponseResult.success(stat);
    }

    @GetMapping(value = "/watch1")
    public ResponseResult watch() throws Exception {
        // 监视某个节点的数据变化
        // arg1:连接对象
        // arg2:监视的节点路径
        NodeCache nodeCache=new NodeCache(client,"/watcher1");
        // 启动监视器对象
        nodeCache.start();
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            // 节点变化时回调的方法
            public void nodeChanged() throws Exception {
                System.out.println(nodeCache.getCurrentData().getPath());
                System.out.println(new String(nodeCache.getCurrentData().getData()));
            }
        });
        Thread.sleep(100000);
        System.out.println("结束");
        //关闭监视器对象
        nodeCache.close();

    return ResponseResult.success(123);

    }

    @GetMapping(value = "/watch2")
    public void watcher2() throws Exception {
        // 监视子节点的变化
        // arg1:连接对象
        // arg2:监视的节点路径
        // arg3:事件中是否可以获取节点的数据
        PathChildrenCache pathChildrenCache=new PathChildrenCache(client,"/watcher1",true);
        // 启动监听
        pathChildrenCache.start();
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            // 当子节点方法变化时回调的方法
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                // 节点的事件类型
                System.out.println(pathChildrenCacheEvent.getType());
                // 节点的路径
                System.out.println(pathChildrenCacheEvent.getData().getPath());
                // 节点数据
                System.out.println(new String(pathChildrenCacheEvent.getData().getData()));
            }
        });
        Thread.sleep(100000);
        System.out.println("结束");
        // 关闭监听
        pathChildrenCache.close();

    }

    @GetMapping(value = "/tran")
    public void tra1() throws Exception {
        // 开启事务
        client.inTransaction()
                .create().forPath("/node1","node1".getBytes())
                .and()
                .setData().forPath("/node2","node2".getBytes())
                .and()
                //事务提交
                .commit();
    }


    @GetMapping(value = "/lock1")
    public void lock1() throws Exception {
        // 排他锁
        // arg1:连接对象
        // arg2:节点路径
        InterProcessLock interProcessLock = new InterProcessMutex(client, "/lock1");
        System.out.println("等待获取锁对象!");
        // 获取锁
        interProcessLock.acquire();
        for (int i = 1; i <= 10; i++) {
            Thread.sleep(3000);
            System.out.println(i);
        }
        // 释放锁
        interProcessLock.release();
        System.out.println("等待释放锁!");
    }

    @GetMapping(value = "/lock2")
    public void lock2() throws Exception {
        // 读写锁
        InterProcessReadWriteLock interProcessReadWriteLock=new InterProcessReadWriteLock(client, "/lock1");
        // 获取读锁对象
        InterProcessLock interProcessLock=interProcessReadWriteLock.readLock();
        System.out.println("等待获取锁对象!");
        // 获取锁
        interProcessLock.acquire();
        for (int i = 1; i <= 10; i++) {
            Thread.sleep(3000);
            System.out.println(i);
        }
        // 释放锁
        interProcessLock.release();
        System.out.println("等待释放锁!");
    }

    @GetMapping(value = "/lock3")
    public void lock3() throws Exception {
        // 读写锁
        InterProcessReadWriteLock interProcessReadWriteLock=new InterProcessReadWriteLock(client, "/lock1");
        // 获取写锁对象
        InterProcessLock interProcessLock=interProcessReadWriteLock.writeLock();
        System.out.println("等待获取锁对象!");
        // 获取锁
        interProcessLock.acquire();
        for (int i = 1; i <= 10; i++) {
            Thread.sleep(3000);
            System.out.println(i);
        }
        // 释放锁
        interProcessLock.release();
        System.out.println("等待释放锁!");
    }


}
