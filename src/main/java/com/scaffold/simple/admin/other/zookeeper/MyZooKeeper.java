//package com.scaffold.simple.admin.other.zookeeper;
//
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.apache.zookeeper.CreateMode;
//import org.apache.zookeeper.KeeperException;
//import org.apache.zookeeper.ZooDefs;
//import org.apache.zookeeper.ZooKeeper;
//import org.apache.zookeeper.data.ACL;
//import org.apache.zookeeper.data.Stat;
//
//import javax.imageio.spi.ServiceRegistry;
//import java.io.IOException;
//import java.text.MessageFormat;
//import java.util.List;
//import java.util.Objects;
//import java.util.concurrent.CountDownLatch;
//
///**
// * @Author: tianjl
// * @Date: 2021/1/19 14:58
// * @Eamil: 2695062879@qq.com
// */
//public class MyZooKeeper {
//
//    private static Logger logger = LogManager.getLogger(MyZooKeeper.class);
//
//
//     static final String server = "127.0.0.1:2181";
//
//     static int timeout = 3000;
//
//    public static ZooKeeper connect() throws IOException, InterruptedException {
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        System.out.println("prepare...");
//        ZooKeeper zk = new ZooKeeper(server, timeout, new MyZkWatcher("123"));
//        System.out.println("complate connect..");
////        if (!Objects.isNull(zk)){
////            countDownLatch.countDown();
////        }
////        countDownLatch.await();
//        return zk;
//    }
//
//    public static ZooKeeper reconnect(Long sessionId, Byte[] password) throws IOException, InterruptedException {
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        System.out.println("reconnection");
//        ZooKeeper zooKeeper = new ZooKeeper(server, timeout, new MyZkWatcher("234"));
//        System.out.println("reconnection commplate");
//        countDownLatch.await();
//        return zooKeeper;
//    }
//
//    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
//        ZooKeeper zk = connect();
//        System.out.println("zk state:" + zk.getState());
//        /**恢复会话连接**/
//        //long sessionId = zk.getSessionId();
//        //byte[] sessionPasswd = zk.getSessionPasswd();
//        //zk2会话重连后，zk会话将失效，不再支持做增删改查操作。
//        //ZooKeeper zk2 = reconnect(sessionId, sessionPasswd);
//
//        /**创建节点**/
//        create(zk, "/tianjingle", "tianjl");
//
//        /**查询节点Data**/
//        queryData(zk, "/tianjingle");
//
//        /**修改节点data**/
//        update(zk, "/tianjingle", "zhangsan");
//
//        /**删除节点**/
//        delete(zk, "/tianjingle");
//    }
//
//
//    static  void create(ZooKeeper zk,String nodePath,String nodeData) throws KeeperException, InterruptedException{
//        System.out.println(MessageFormat.format("开始创建节点：{0}， 数据：{1}",nodePath,nodeData));
//        List<ACL> acl = ZooDefs.Ids.OPEN_ACL_UNSAFE;
//        CreateMode createMode = CreateMode.PERSISTENT;
//        String result = zk.create(nodePath, nodeData.getBytes(), acl, createMode);
//        //创建节点有两种，上面是第一种，还有一种可以使用回调函数及参数传递，与上面方法名称相同。
//        System.out.println(MessageFormat.format("创建节点返回结果：{0}",result));
//        System.out.println(MessageFormat.format("完成创建节点：{0}， 数据：{1}",nodePath,nodeData));
//    }
//
//    /**
//     * 描述：查询节点结构信息
//     * 作者：七脉
//     * @param zk
//     * @param nodePath
//     * @return
//     * @throws KeeperException
//     * @throws InterruptedException
//     */
//    public static Stat queryStat(ZooKeeper zk, String nodePath) throws KeeperException, InterruptedException{
//        System.out.println(MessageFormat.format("准备查询节点Stat，path：{0}", nodePath));
//        Stat stat = zk.exists(nodePath, false);
//        System.out.println(MessageFormat.format("结束查询节点Stat，path：{0}，version：{1}", nodePath, stat.getVersion()));
//        return stat;
//    }
//
//    /**
//     * 描述：查询节点Data值信息
//     * 作者：七脉
//     * @param zk
//     * @param nodePath
//     * @return
//     * @throws KeeperException
//     * @throws InterruptedException
//     */
//    public static String queryData(ZooKeeper zk,String nodePath) throws KeeperException, InterruptedException{
//        System.out.println(MessageFormat.format("准备查询节点Data,path：{0}", nodePath));
//        String data = new String(zk.getData(nodePath, false, queryStat(zk, nodePath)));
//        System.out.println(MessageFormat.format("结束查询节点Data,path：{0}，Data：{1}", nodePath, data));
//        return data;
//    }
//
//
//    /**
//     * 描述：修改节点
//     * 作者：七脉
//     * @param zk
//     * @param nodePath
//     * @param nodeData
//     * @throws KeeperException
//     * @throws InterruptedException
//     * 重点：每次修改节点的version版本号都会变更，所以每次修改都需要传递节点原版本号，以确保数据的安全性。
//     */
//    public static Stat update(ZooKeeper zk,String nodePath,String nodeData) throws KeeperException, InterruptedException{
//        //修改节点前先查询该节点信息
//        Stat stat = queryStat(zk, nodePath);
//        System.out.println(MessageFormat.format("准备修改节点，path：{0}，data：{1}，原version：{2}", nodePath, nodeData, stat.getVersion()));
//        Stat newStat = zk.setData(nodePath, nodeData.getBytes(), stat.getVersion());
//        //修改节点值有两种方法，上面是第一种，还有一种可以使用回调函数及参数传递，与上面方法名称相同。
//        //zk.setData(path, data, version, cb, ctx);
//        System.out.println(MessageFormat.format("完成修改节点，path：{0}，data：{1}，现version：{2}", nodePath, nodeData, newStat.getVersion()));
//        return stat;
//    }
//
//    /**
//     * 描述：删除节点
//     * 作者：七脉
//     * @param zk
//     * @param nodePath
//     * @throws InterruptedException
//     * @throws KeeperException
//     */
//    public static void delete(ZooKeeper zk,String nodePath) throws InterruptedException, KeeperException{
//        //删除节点前先查询该节点信息
//        Stat stat = queryStat(zk, nodePath);
//        System.out.println(MessageFormat.format("准备删除节点，path：{0}，原version：{1}", nodePath, stat.getVersion()));
//        zk.delete(nodePath, stat.getVersion());
//        //修改节点值有两种方法，上面是第一种，还有一种可以使用回调函数及参数传递，与上面方法名称相同。
//        //zk.delete(path, version, cb, ctx);
//        System.out.println(MessageFormat.format("完成删除节点，path：{0}", nodePath));
//    }
//}
