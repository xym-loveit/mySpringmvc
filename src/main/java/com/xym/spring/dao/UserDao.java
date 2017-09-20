package com.xym.spring.dao;

import com.xym.spring.module.User;
import org.springframework.stereotype.Repository;

/**
 * desc
 *
 * @author xym
 */
@Repository
public class UserDao {


    public User queryUserByUserName(String username) {
        if (username.equals("admin")) {
            User user = new User();
            user.setUserName("admin");
            user.setPassword("123");
            return user;
        }
        return null;
    }
}