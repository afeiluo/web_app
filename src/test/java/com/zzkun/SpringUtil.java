package com.zzkun;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zzkun.model.User;
import com.zzkun.service.UserService;

public class SpringUtil {
    /**
     * @param args
     */
    public static void main(String[] args) {

        UserService um = (UserService) SpringUtil.getBean("userService");
        User user = new User();
        user.setUsername("xxx");
        user.setPassword("xxx");
        um.register(user);
    }

    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("/springmvc-servlet.xml");

    public static Object getBean(String beanName) {
        return ctx.getBean(beanName);
    }
}
