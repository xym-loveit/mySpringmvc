package com.xym.spring.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/8/2.
 */
@Controller
public class HelloController implements EnvironmentAware{

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

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
