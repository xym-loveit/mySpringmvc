package com.xym.spring.controller;

import com.xym.spring.module.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * desc
 *
 * @author xym
 */
@Controller
@RequestMapping("/user")
public class RegisterController {


    @RequestMapping("index")
    public String index() {
        return "register";
    }

    @RequestMapping("register")
    public String register(HttpServletRequest request, @Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            for (FieldError fieldError : result.getFieldErrors()) {
                request.setAttribute("ERR_" + fieldError.getField(), fieldError.getDefaultMessage());
            }
            return "register";
        }
        return "registerSuccess";
    }
}