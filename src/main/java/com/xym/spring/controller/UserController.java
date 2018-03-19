package com.xym.spring.controller;

import com.xym.spring.module.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * SessionAttributes和ModelAttribute作用原理
 * <p>
 * 对应说明SessionAttributes和ModelAttribute作用原理.png
 *
 * @author xym
 */
@Controller
@RequestMapping("user")
@SessionAttributes("user")
public class UserController {


    /**
     * 每次请求其他方法前先执行此方法，并以domin为键添加到模型中
     *
     * @return
     */
    @ModelAttribute("user")
    public User getDomain() {
        User user = new User();
        user.setUserName("userName");
        user.setPassword("123456");
        return user;
    }

    @RequestMapping("hd1")
    public String handler1(@ModelAttribute("user") User user) {
        user.setUserName("uuu");
        return "redirect:/user/hd2";
    }

    @RequestMapping("hd2")
    public String handler2(ModelMap modelMap, SessionStatus sessionStatus) {
        /*读取模型中的数据*/
        User user = (User) modelMap.get("user");
        if (user != null) {
            System.out.println(user.getUserName());
            user.setUserName("handler2");
            /**
             * 让SpringMVC清除本处理器对应的会话属性
             */
            sessionStatus.setComplete();
        }
        return "showUser";
    }

}
