package com.foam.gene.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/Tools")
public class PCAController {
    @RequestMapping("/get_file")
    public String get_file(@RequestParam("file_type") String file_type,
                           MultipartFile file,
                           @RequestParam("PCA_number") String PCA_number,
                           @RequestParam("Email") String Email ,
                           @RequestParam("Theme") String Theme) throws IOException {
        System.out.println(file_type);

        // 文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println("文件名："+file.getOriginalFilename());
        System.out.println("文件大小："+file.getSize());
        System.out.println("PCA参数是"+PCA_number);
        System.out.println("邮箱地址是"+Email);
        System.out.println("本次主题是"+Theme);

        // 改文件名
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = "test";

        // 保存用户上传的文件
        file.transferTo(new File("/Users/lizhikun/Desktop/test", newFileName));
        System.out.println("已保存在/Users/lizhikun/Desktop/test路径下");

        //设置执行命令

        return "Download";
    }

}
