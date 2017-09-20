package com.xym.spring.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/8/2.
 */
@Controller
public class HelloController implements EnvironmentAware {

    private final Log logger = LogFactory.getLog(HelloController.class);

    private Environment environment;

    @RequestMapping(value = {"/"}, method = {RequestMethod.HEAD})
    public String head() {
        return "head.jsp";
    }


    @RequestMapping(value = {"/index", "/"}, method = {RequestMethod.GET})
    public String index(ModelMap modelMap) {
        logger.info("--processed by index--");
        modelMap.addAttribute("result", "index mapping");
        return "head.jsp";
    }

    @RequestMapping(value = "/testRedirect", method = {RequestMethod.POST})
    public String testRedirect(RedirectAttributes redirectAttributes, HttpServletRequest request) {
        System.out.println("重定向参数传递测试");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
       /* FlashMap flashMap = ((FlashMap) requestAttributes.getRequest().getAttribute(DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE));
        flashMap.put("key_x", "redirect重定向通过FlashMap实现参数传递");*/
        ((FlashMap) requestAttributes.getRequest().getAttribute(DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE)).put("key_x", "redirect重定向通过FlashMap实现参数传递");


        //第二种获取flashMap的方式
        FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);


        redirectAttributes.addAttribute("key_y", "redirect重定向通过FlashMap实现参数传递");
        redirectAttributes.addFlashAttribute("key_z", "redirect重定向通过FlashMap实现参数传递");
        return "redirect:showRedirect";
    }


    @RequestMapping(value = "/showRedirect", method = {RequestMethod.GET})
    public String showRedirect(Model model) {
        return "redirect.jsp";
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
