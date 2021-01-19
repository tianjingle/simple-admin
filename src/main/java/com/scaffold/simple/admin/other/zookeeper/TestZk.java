package com.scaffold.simple.admin.other.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;

/**
 * @Author: tianjl
 * @Date: 2021/1/19 16:02
 * @Eamil: 2695062879@qq.com
 */
@RestController
@RequestMapping(value = "/zk")
public class TestZk {

    @Autowired
    private ZooKeeper zk;


    @GetMapping(value = "/test")
    public void add() throws KeeperException, InterruptedException {

        /**创建节点**/
        create(zk, "/tianjingle", "tianjl");

        /**查询节点Data**/
        queryData(zk, "/tianjingle");

        /**修改节点data**/
        update(zk, "/tianjingle", "zhangsan");

        /**删除节点**/
//        delete(zk, "/tianjingle");
    }

    static  void create(ZooKeeper zk,String nodePath,String nodeData) throws KeeperException, InterruptedException{
        System.out.println(MessageFormat.format("开始创建节点：{0}， 数据：{1}",nodePath,nodeData));
        List<ACL> acl = ZooDefs.Ids.OPEN_ACL_UNSAFE;
        CreateMode createMode = CreateMode.PERSISTENT;
        String result = zk.create(nodePath, nodeData.getBytes(), acl, createMode);
        //创建节点有两种，上面是第一种，还有一种可以使用回调函数及参数传递，与上面方法名称相同。
        System.out.println(MessageFormat.format("创建节点返回结果：{0}",result));
        System.out.println(MessageFormat.format("完成创建节点：{0}， 数据：{1}",nodePath,nodeData));
    }

    /**
     * 描述：查询节点结构信息
     * 作者：七脉
     * @param zk
     * @param nodePath
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public static Stat queryStat(ZooKeeper zk, String nodePath) throws KeeperException, InterruptedException{
        System.out.println(MessageFormat.format("准备查询节点Stat，path：{0}", nodePath));
        Stat stat = zk.exists(nodePath, false);
        System.out.println(MessageFormat.format("结束查询节点Stat，path：{0}，version：{1}", nodePath, stat.getVersion()));
        return stat;
    }

    /**
     * 描述：查询节点Data值信息
     * 作者：七脉
     * @param zk
     * @param nodePath
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public static String queryData(ZooKeeper zk,String nodePath) throws KeeperException, InterruptedException{
        System.out.println(MessageFormat.format("准备查询节点Data,path：{0}", nodePath));
        String data = new String(zk.getData(nodePath, false, queryStat(zk, nodePath)));
        System.out.println(MessageFormat.format("结束查询节点Data,path：{0}，Data：{1}", nodePath, data));
        return data;
    }


    /**
     * 描述：修改节点
     * 作者：七脉
     * @param zk
     * @param nodePath
     * @param nodeData
     * @throws KeeperException
     * @throws InterruptedException
     * 重点：每次修改节点的version版本号都会变更，所以每次修改都需要传递节点原版本号，以确保数据的安全性。
     */
    public static Stat update(ZooKeeper zk,String nodePath,String nodeData) throws KeeperException, InterruptedException{
        //修改节点前先查询该节点信息
        Stat stat = queryStat(zk, nodePath);
        System.out.println(MessageFormat.format("准备修改节点，path：{0}，data：{1}，原version：{2}", nodePath, nodeData, stat.getVersion()));
        Stat newStat = zk.setData(nodePath, nodeData.getBytes(), stat.getVersion());
        //修改节点值有两种方法，上面是第一种，还有一种可以使用回调函数及参数传递，与上面方法名称相同。
        //zk.setData(path, data, version, cb, ctx);
        System.out.println(MessageFormat.format("完成修改节点，path：{0}，data：{1}，现version：{2}", nodePath, nodeData, newStat.getVersion()));
        return stat;
    }

    /**
     * 描述：删除节点
     * 作者：七脉
     * @param zk
     * @param nodePath
     * @throws InterruptedException
     * @throws KeeperException
     */
    public static void delete(ZooKeeper zk,String nodePath) throws InterruptedException, KeeperException{
        //删除节点前先查询该节点信息
        Stat stat = queryStat(zk, nodePath);
        System.out.println(MessageFormat.format("准备删除节点，path：{0}，原version：{1}", nodePath, stat.getVersion()));
        zk.delete(nodePath, stat.getVersion());
        //修改节点值有两种方法，上面是第一种，还有一种可以使用回调函数及参数传递，与上面方法名称相同。
        //zk.delete(path, version, cb, ctx);
        System.out.println(MessageFormat.format("完成删除节点，path：{0}", nodePath));
    }

}
