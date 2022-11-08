package com.foam.gene.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/Tools")
public class PCAController {
    @RequestMapping("/get_file")
    public String get_file(@RequestParam("file_type") String file_type,
                           MultipartFile file,
                           @RequestParam("PCA_number") String PCA_number,
                           @RequestParam("Email") String Email ,
                           @RequestParam("Theme") String Theme ,
                           HttpServletResponse response) throws IOException, InterruptedException {
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
        String newFileName = Theme+ext;
        String FilePrefix = Theme;

        // 保存用户上传的文件
        // file.transferTo(new File("/Users/lizhikun/Desktop/test", newFileName));
        //file.transferTo(new File("/Users/zzzlin/Desktop/test", newFileName));

        file.transferTo(new File("/home/foam/gene/pca_model/users/"+Email+"/start",newFileName));
        Process process = Runtime.getRuntime().exec("sh /home/foam/gene/pca_model/script_run/AMO2P-PCA.sh "+Email+" "+FilePrefix+" "+PCA_number);

        Thread.sleep(1000*1);
        // 开启一个文件对象
        File gz = new File("/home/foam/gene/pca_model/users/"+Email+"/output/"+Theme+".gz");

        // 判断文件是否存在
        if (gz.exists() ) {
            response.reset();
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.setContentLength((int) gz.length());
            response.setHeader("Content-Disposition", "attachment;filename="+Theme+".gz");

            try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(gz));) {
                byte[] buff = new byte[1024];
                OutputStream os  = response.getOutputStream();
                int i = 0;
                while ((i = bis.read(buff)) != -1) {
                    os.write(buff, 0, i);
                    os.flush();
                }
            } catch (IOException e) {
                return "下载失败";
            }
        } else {
            System.out.println("文件不存在");
            return "出错了";
        }
        return "Download";
    }

}
