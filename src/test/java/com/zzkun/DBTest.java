package com.zzkun;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zzkun.model.User;
import com.zzkun.service.UserService;

//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:springmvc-servlet.xml"})
public class DBTest {

    @Autowired
    private UserService userService;

    //@Test
    public void testSout() {
        System.out.println("dddd");
    }

    //@Test
    public void testDB() {
        List<User> retUsers = userService.findUserByPage(2, 20);
        for (User user : retUsers) {
            System.out.println(user);
        }
    }

    @Test
    public void testAdd() {
        User user = new User();
        user.setId(11);
        user.setUsername("dfwef");
        user.setPassword("eeee");
        userService.register(user);
    }
}
