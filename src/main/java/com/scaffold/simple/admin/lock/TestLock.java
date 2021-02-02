package com.scaffold.simple.admin.lock;

import com.scaffold.simple.admin.lock.curator.ReSubmitLock;
import com.scaffold.simple.admin.utils.ResponseResult;
import org.apache.zookeeper.KeeperException;
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



    @GetMapping(value = "/{userId}/{token}")
    public ResponseResult test(@PathVariable String userId,@PathVariable String token) throws KeeperException, InterruptedException {
        boolean f=lockTest.check(userId,token);
        if (f){
            System.out.println("提交成功！");
            return ResponseResult.success("ok");
        }else{
            System.out.println("重复提交表单");
            lockTest.generate(userId);
            return ResponseResult.success("false");
        }
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
