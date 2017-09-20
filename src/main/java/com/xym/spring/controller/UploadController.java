package com.xym.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * desc
 *
 * @author xym
 */
@Controller
public class UploadController {


    @RequestMapping("/upindex")
    public String index() {
        return "oneUpload";
    }


    @RequestMapping("/oneUpload")
    public String oneUpload(@RequestParam("imgfile") CommonsMultipartFile file, HttpServletRequest request) {
        String ctxPath = request.getServletContext().getRealPath("/");
        System.out.println("ctxPath=" + ctxPath);
        File file1 = new File(ctxPath + "/upload");
        if (!file1.exists()) {
            file1.mkdirs();
        }

        System.out.println(file1.getAbsolutePath());
        File file2 = new File(file1.getAbsolutePath() + "/" + file.getOriginalFilename());
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            file.transferTo(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:http://localhost:8080/upload/" + file.getOriginalFilename();
    }

}