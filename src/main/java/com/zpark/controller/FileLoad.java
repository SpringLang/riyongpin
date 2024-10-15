package com.zpark.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
public class FileLoad {

    public static String upload( MultipartFile file) throws IOException {
        String n = UUID.randomUUID().toString();
        //获取文件名字
        String fileName = n.substring(0,5)+file.getOriginalFilename();
        //设置编译后文件存在路径

        //获取项目路径
        String path2="E:/2324javakeshe/日用品/riyongpin/src/main/webapp/images/"+fileName;
        String path="E:/2324javakeshe/日用品/riyongpin/target/Blog/images/"+fileName;

        FileOutputStream fileOutputStream = new FileOutputStream(path2);

        FileOutputStream fileOutputStream2 = new FileOutputStream(path);

        InputStream inputStream = file.getInputStream();
        int bytesRead = 0;
        byte[] buffer = new byte[102400];
        while ((bytesRead = inputStream.read(buffer, 0, 102400)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
            fileOutputStream2.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        fileOutputStream.close();
        String visitUrl = "/images/"+fileName;
        return visitUrl;
    }
}
