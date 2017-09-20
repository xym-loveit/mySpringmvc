package com.xym.spring.controller;

import com.xym.spring.constant.Global;
import com.xym.spring.module.User;
import com.xym.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * desc
 *
 * @author xym
 */
@Controller
@RequestMapping("/u")
public class LoginController {

    @ExceptionHandler(value = Exception.class)
    public String handlerException(Exception e, HttpServletRequest request) {
        request.setAttribute("error", e.getMessage());
        return "error";
    }

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String toLoginPage() {
        return "login";
    }

    @RequestMapping("/usercenter")
    public String usercenter() {
        return "usercenter";
    }

    @RequestMapping("/error")
    public void error() {
        throw new RuntimeException("异常测试");
    }

    @RequestMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete();
        System.out.println("退出，清理session");
        return "login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        try {
            User user = userService.queryUser(username, password);
            if (user != null) {
                request.getSession().setAttribute(Global.USER_SESSION_KEY, user);
                return "usercenter";
            }
        } catch (Exception e) {
            //e.printStackTrace();
            request.setAttribute("error", e.getMessage());
        }
        return "login";
    }
}