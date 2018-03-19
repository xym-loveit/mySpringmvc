package com.xym.spring.controller;

import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.xym.spring.module.MyDomain;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

/**
 * RequestBody/ResponseBody 请求/响应的转换情况
 * HttpEntity/ResponseEntity 请求/响应的转换情况
 * <p>
 * 测试MessageConvert的几种情况
 *
 * @author xym
 */
public class MessageConvertControllerTest {


    @Test
    public void testHd1() {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("username", "aaa");
        params.add("password", "123");
        params.add("age", "25");
        restTemplate.postForLocation("http://localhost:8080/convert/hd1", params);
    }

    @Test
    public void testHd2() {
        RestTemplate restTemplate = new RestTemplate();
        byte[] bytes = restTemplate.postForObject("http://localhost:8080/convert/hd2/{imageId}", null, byte[].class, "123");
        Resource fileSystemResource = new FileSystemResource("d:/copy_img.jpg");
        try {
            FileCopyUtils.copy(bytes, fileSystemResource.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testHd3() {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("username", "aaa");
        params.add("password", "123");
        params.add("age", "25");
        restTemplate.postForLocation("http://localhost:8080/convert/hd3", params);
    }

    @Test
    public void testHd4() {
        RestTemplate restTemplate = new RestTemplate();
        byte[] bytes = restTemplate.postForObject("http://localhost:8080/convert/hd4/{imageId}", null, byte[].class, "123");
        Resource fileSystemResource = new FileSystemResource("d:/copy_img.jpg");
        try {
            FileCopyUtils.copy(bytes, fileSystemResource.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHd5() {
        RestTemplate restTemplate = buildRestTemplate();
        MyDomain myDomain = new MyDomain();
        myDomain.setId("10");
        myDomain.setPasspwd("123456");
        myDomain.setRealName("张三");
        myDomain.setUsername("zhangsan");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_XML);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));

        HttpEntity<MyDomain> myDomainHttpEntity = new HttpEntity<MyDomain>(myDomain, httpHeaders);
        ResponseEntity<MyDomain> exchange = restTemplate.exchange("http://localhost:8080/convert/hd5", HttpMethod.POST, myDomainHttpEntity, MyDomain.class);
        System.out.println(exchange.getBody().getRealName());
        System.out.println(exchange.getBody().getUsername());
        System.out.println(exchange.getBody().getPasspwd());
        System.out.println(exchange.getBody().getId());

    }

    @Test
    public void testHd6() {
        RestTemplate restTemplate = buildRestTemplate();
        MyDomain myDomain = new MyDomain();
        myDomain.setId("10");
        myDomain.setPasspwd("123456");
        myDomain.setRealName("张三");
        myDomain.setUsername("zhangsan");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<MyDomain> myDomainHttpEntity = new HttpEntity<MyDomain>(myDomain, httpHeaders);
        ResponseEntity<MyDomain> exchange = restTemplate.exchange("http://localhost:8080/convert/hd5", HttpMethod.POST, myDomainHttpEntity, MyDomain.class);
        System.out.println(exchange.getBody().getRealName());
        System.out.println(exchange.getBody().getUsername());
        System.out.println(exchange.getBody().getPasspwd());
        System.out.println(exchange.getBody().getId());

    }

    private RestTemplate buildRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
        xStreamMarshaller.setStreamDriver(new StaxDriver());
        xStreamMarshaller.setAnnotatedClasses(new Class[]{MyDomain.class});

        MarshallingHttpMessageConverter marshallingHttpMessageConverter = new MarshallingHttpMessageConverter();
        marshallingHttpMessageConverter.setMarshaller(xStreamMarshaller);
        marshallingHttpMessageConverter.setUnmarshaller(xStreamMarshaller);

        restTemplate.getMessageConverters().add(marshallingHttpMessageConverter);

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        return restTemplate;
    }

}
