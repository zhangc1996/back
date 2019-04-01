package com.neuedu.test;

import com.neuedu.pojo.User;
import com.neuedu.service.UserService;
import com.neuedu.util.DESUTIL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MyTest {
    @Resource
    UserService userService;
    @Test
    public void method(){
        User user = new User();
        user.setLoginId("admin");
        user.setName("张超");
        user.setPassword(DESUTIL.enCode("123456"));
        user.setPhone("12345678901");
        user.setEmail("123@123.com");
        user.setSex(1);
        user.setCreateDate(new Date());
        System.out.println(userService.userAdd(user));
    }
}
