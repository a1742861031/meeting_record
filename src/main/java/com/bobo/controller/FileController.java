package com.bobo.controller;

import com.bobo.utils.AliyunOSSUtils;
import com.bobo.vo.MyException;
import com.bobo.vo.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 文件上传
 * @Date 2022/1/16 12:25 PM
 * @Created by bobo
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        //如果文件为空 返回错误信息
        if(file.isEmpty()){
            throw new MyException(new R("201","文件列表未空"));
        }
        //获取原文件名
        String originalFilename = file.getOriginalFilename();

        //返回图片的url
        return new R(AliyunOSSUtils.uploadFileInputSteam(originalFilename,file));
    }

}
