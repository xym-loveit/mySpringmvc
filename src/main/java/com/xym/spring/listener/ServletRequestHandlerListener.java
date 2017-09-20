package com.xym.spring.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

/**
 * desc
 *
 * @author xym
 */
@Component
public class ServletRequestHandlerListener implements ApplicationListener<ServletRequestHandledEvent> {

    public ServletRequestHandlerListener(){
        System.out.println("ServletRequestHandlerListener init");
    }

    public void onApplicationEvent(ServletRequestHandledEvent event) {
        System.out.println("ServletRequestHandlerListener ServletRequestHandledEvent");
        System.out.println(event.getDescription());
    }
}