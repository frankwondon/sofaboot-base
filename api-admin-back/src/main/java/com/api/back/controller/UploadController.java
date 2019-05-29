package com.api.back.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.module.common.Response;
import com.module.common.ResponseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

@Api("上传接口")
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Value("${upload.file.img}")
    private String uploadImgFile;
    @Value("${upload.file.video}")
    private String uploadVideoFile;
    @ApiOperation("上传图片限制大小20M 限制PNG JPG")
    @PostMapping("uploadImg")
    public Response uploadImg(@RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String sub = StrUtil.subAfter(originalFilename, '.', true);
        if ( !ReUtil.isMatch("(jpg|png)",sub)){
            return Response.fail(ResponseCode.C_500101);
        }
        //获取当前的日期yyyyMMdd 用于生成文件目录
        return getResponse(file, sub,uploadImgFile);
    }


    @ApiOperation("上传视频文件限制大小20M")
    @PostMapping("uploadVideo")
    public Response uploadVideo(@RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String sub = StrUtil.subAfter(originalFilename, '.', true);
        if ( !ReUtil.isMatch("(mp4)",sub)){
            return Response.fail(ResponseCode.C_500101);
        }
        //获取当前的日期yyyyMMdd 用于生成文件目录
        return getResponse(file, sub,uploadVideoFile);
    }

    private Response getResponse(MultipartFile file, String sub,String path) {
        String s = StrUtil.removeAll(LocalDate.now().toString(), "-");
        String dir=path + File.separator +s+File.separator;
        File localFile = new File(dir);
        try {
            if (!localFile.exists()) {
                localFile.mkdirs();
            }
            localFile=new File(dir+ IdUtil.fastSimpleUUID()+"."+sub);
            if (!localFile.exists()){
                boolean newFile = localFile.createNewFile();
                if (newFile){
                    file.transferTo(localFile);
                    return Response.success(true);
                }
            }
        } catch (IOException e) {
            return Response.fail(ResponseCode.C_500100);
        }
        return Response.fail(ResponseCode.C_500100);
    }


}
