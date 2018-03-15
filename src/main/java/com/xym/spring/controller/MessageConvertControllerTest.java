package com.xym.spring.controller;

import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

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
}
