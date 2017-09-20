package com.xym.spring.controller;

import com.google.common.collect.Lists;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * desc
 *
 * @author xym
 */
@Controller
public class DownLoadController {

    @RequestMapping("downindex")
    public String index(HttpServletRequest request) {
        String s = request.getServletContext().getRealPath("/") + "/upload";
        File file = new File(s);
        if (file.exists() && file.isDirectory()) {
            List<File> files = Lists.newArrayList();
            for (File file1 : file.listFiles()) {
                files.add(file1);
            }
            request.setAttribute("files", files);
        }
        return "downindex";
    }

    /**
     * 第一种下载方式
     *
     * @param fileName
     * @param request
     * @param response
     */
    @RequestMapping("downloadfile")
    public void download(@RequestParam("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) {
        //设置响应头和客户端保存文件名
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        FileInputStream fileInputStream = null;
        BufferedInputStream reader = null;
        OutputStream os = null;
        byte[] bytes = null;
        try {
            String s = request.getServletContext().getRealPath("/") + "/upload";
            fileInputStream = new FileInputStream(s + File.separator + fileName);
            reader = new BufferedInputStream(fileInputStream);
            os = response.getOutputStream();
            bytes = new byte[2048];
            try {
                int bytereads = -1;
                while (-1 != (bytereads = reader.read(bytes, 0, bytes.length))) {
                    os.write(bytes, 0, bytereads);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                reader.close();
                fileInputStream.close();
                bytes = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @RequestMapping("downloadfile2")
    public ResponseEntity<byte[]> downloadfile2(@RequestParam("fileName") String fileName, HttpServletRequest request) {
        String s = request.getServletContext().getRealPath("/") + "/upload";
        try {
            FileInputStream fileInputStream = new FileInputStream(s + File.separator + fileName);
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attchement;filename=" + fileName);
            HttpStatus statusCode = HttpStatus.OK;
            ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(bytes, headers, statusCode);
            return entity;
        } catch (IOException e) {
            e.printStackTrace();
        }
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(new byte[0], new HttpHeaders(), HttpStatus.NOT_FOUND);
        return entity;
    }

}