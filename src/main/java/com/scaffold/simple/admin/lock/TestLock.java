package com.scaffold.simple.admin.lock;

import com.scaffold.simple.admin.lock.annotation.DoubleSubmitAnnotation;
import com.scaffold.simple.admin.lock.curator.ReSubmitLock;
import com.scaffold.simple.admin.other.jclass.LockTest;
import com.scaffold.simple.admin.utils.ResponseResult;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tianjl
 * @Date: 2021/1/29 16:30
 * @Eamil: 2695062879@qq.com
 */
@RestController
@RequestMapping(value = "/zklock")
public class TestLock {

    @Autowired
    private LockTest lockTest;

    /**
     * 放重复提交
     */
    @Autowired
    private ReSubmitLock reSubmitLock;

    /**
     * 进行一些操作
     */
    @Autowired
    private CuratorFramework client;

    /**
     * 校验并生成下次凭证
     * @return
     */
    @DoubleSubmitAnnotation(check = true,generate = true)
    @GetMapping(value = "/test")
    public ResponseResult test() {
        return ResponseResult.success(123);
    }

    /**
     * 生成新token
     * @return
     */
    @DoubleSubmitAnnotation(generate = true)
    @GetMapping(value = "/gen")
    public ResponseResult test1() {
        return ResponseResult.success(123);
    }


    @GetMapping(value = "/tian")
    public ResponseResult mylock() throws Exception {
        InterProcessMutex mutex = new InterProcessMutex( client, "/mutex/resource" );
        mutex.acquire();
        System.out.println("执行代码");
        mutex.release();
        return ResponseResult.success(123);
    }
    /**
     * 加锁
     * @param userId
     * @param token
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/ReSubmitLock/{userId}/{token}")
    public ResponseResult test1(@PathVariable String userId,@PathVariable String token) throws Exception {
        boolean f=reSubmitLock.check(userId,token);
        if (f){
            System.out.println("提交成功！");
            return ResponseResult.success("ok");
        }else{
            System.out.println("重复提交表单");
            String newToken=reSubmitLock.generateToken(userId);
            return ResponseResult.error(newToken);
        }
    }

}
