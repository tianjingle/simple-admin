package com.scaffold.simple.admin.api;

import com.scaffold.simple.admin.SimpleAdminService;
import com.scaffold.simple.admin.api.TestApi;
import com.scaffold.simple.admin.application.dto.user.OOO;
import com.scaffold.simple.admin.application.impl.TestOOO;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author tianjl
 * @Date 2021/11/17 17:32
 * @Discription disc
 */
@SpringBootTest(classes = SimpleAdminService.class)
@RunWith(SpringRunner.class)
public class MocktoTest {

    @Autowired
    private TestApi testApi;

    @Test
    public void test(){

       TestOOO testOOO =  Mockito.mock(TestOOO.class);
       List<OOO> user=new ArrayList<>();
        OOO ooOn=new OOO();
        ooOn.setAge("1");
        ooOn.setName("1");
        user.add(ooOn);
        ooOn.setAge("2");
        ooOn.setName("2");
        user.add(ooOn);
       Mockito.when(testOOO.query("zhang")).thenReturn(user);
       List<OOO> result= testApi.test();
        System.out.println(result.toString());
       Mockito.verify(testApi).test();


    }
}
