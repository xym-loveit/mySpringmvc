package com.xym.spring.controller;

import com.xym.spring.module.MyDomain;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author xym
 */
@Controller
@RequestMapping("/convert")
public class MessageConvertController {


    /**
     * 将请求报文体转换为字符串绑定到requestBody入参中
     * 通过RequestBody对应于StringHttpMessageConverter转换器
     *
     * @param requestBody
     * @return
     */
    @RequestMapping("/hd1")
    public String handler1(@RequestBody String requestBody) {
        System.out.println("requestBody:" + requestBody);
        return "success";
    }


    /**
     * 读取一张图片，并将图片数据输出到响应流中，客户端将显示这张图片
     * 通过ResponseBody对应于方法返回值(byte[])ByteArrayHttpMessageConverter转换器
     *
     * @param imageId
     * @return
     */
    @ResponseBody
    @RequestMapping("/hd2/{imageId}")
    public byte[] handler2(@PathVariable("imageId") String imageId) {
        System.out.println("load image of " + imageId);
        Resource resource = new ClassPathResource("/images/timg.jpg");
        try {
            byte[] bytes = FileCopyUtils.copyToByteArray(resource.getFile());
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用StringHttpMessageConverter转换器将请求报文体和报文头信息绑定到httpEntity中，在方法中可以对相应信息进行访问
     *
     * @param httpEntity
     * @return
     */
    @RequestMapping("/hd3")
    public String handler3(HttpEntity<String> httpEntity) {
        System.out.println("httpEntity.getHeaders:" + httpEntity.getHeaders());
        System.out.println("httpEntity.getBody:" + httpEntity.getBody());
        return "success";
    }

    /**
     * 在方法中创建ResponseEntity<byte[]>对象并返回，ByteArrayHttpMessageConverter转换器负责将其输出到响应流
     *
     * @param imageId
     * @return
     */
    @RequestMapping("/hd4/{imageId}")
    public ResponseEntity<byte[]> handler4(@PathVariable("imageId") String imageId) {
        Resource resource = new ClassPathResource("/images/timg.jpg");
        try {
            byte[] bytes = FileCopyUtils.copyToByteArray(resource.getFile());
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(bytes, HttpStatus.OK);
            return responseEntity;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/hd5")
    public ResponseEntity<MyDomain> handler5(HttpEntity<MyDomain> httpEntity) {
        MyDomain body = httpEntity.getBody();
        body.setId("9999");
        return new ResponseEntity<MyDomain>(body, HttpStatus.OK);
    }

    /**
     * 每次请求其他方法前先执行此方法，并以domin为键添加到模型中
     *
     * @return
     */
    @ModelAttribute("domain")
    public MyDomain getDomain() {
        MyDomain myDomain = new MyDomain();
        myDomain.setUsername("domainName");
        myDomain.setRealName("李四");
        myDomain.setPasspwd("789");
        myDomain.setId("222");
        return myDomain;
    }

    /**
     * 每次请求其他方法前先执行此方法，并以domin为键添加到模型中
     *
     * @return
     */
    @ModelAttribute("aaa")
    public String getAAA() {
        return "--aaa--";
    }

    @RequestMapping("/hd6")
    public String handler6(@ModelAttribute(value = "aaa") String aaa, @ModelAttribute("domain") MyDomain myDomain) {
        System.out.println("aaa=" + aaa);
        myDomain.setRealName("新李四");
        return "show";
    }

    /**
     * SpringMVC将请求对应的隐含模型对象传递给modelMap，因此在方法中可以通过它访问模型中的数据
     *
     * @param vv
     * @param modelMap
     * @return
     */
    @RequestMapping("/hd7")
    public String handler7(@RequestParam("vv") String vv, ModelMap modelMap) {
        if (!modelMap.isEmpty()) {
            Iterator<Map.Entry<String, Object>> iterator = modelMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                System.out.println(next.getKey() + "[--]" + next.getValue());
                if (next.getKey().equals("domain")) {
                    ((MyDomain) next.getValue()).setRealName("modelMap再改一次");
                }
            }
            modelMap.put("other", "贱贱的笑一笑");
        }
        return "show";
    }
}
