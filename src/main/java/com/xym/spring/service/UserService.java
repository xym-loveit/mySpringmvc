package com.xym.spring.service;

import com.xym.spring.dao.UserDao;
import com.xym.spring.module.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * desc
 *
 * @author xym
 */
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public User queryUser(String username, String password) throws Exception {
        User user = userDao.queryUserByUserName(username);
        if (null == username || password == null) {
            throw new Exception("用户名和密码为空");
        }
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}