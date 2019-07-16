package com.api.app.controller.user;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.api.app.query.UpdatePayPwdQuery;
import com.module.api.app.result.UserManagerResult;
import com.module.api.app.service.AppPersonalService;
import com.module.api.app.service.UserService;
import com.module.common.Response;
import com.module.common.ResponseCode;
import com.module.common.bean.AppCurrentUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.time.LocalDate;

/**
 * @ClassName AppPersonalController
 * @Description 个人信息
 * @Author YJT
 * @Date 2019/7/10 0010 9:35
 * @Version 1.0
 **/

@RequestMapping("appPerson")
@RestController
@Api(tags = "个人信息")
@Slf4j
public class AppPersonalController {

    @Value("${upload.file.base}")
    private String base;
    @Value("${upload.file.img}")
    private String uploadImgFile;

    @SofaReference
    private AppPersonalService appPersonalService;
    @SofaReference
    private UserService userService;


    /**
     * @param user
     * @return
     */
    @PostMapping("personalMsg")
    @ApiOperation(value = "个人信息获取")
    public Response<UserManagerResult> personalMsg(@ApiIgnore AppCurrentUser user){
        return Response.success(appPersonalService.personalMsg(user.getId()));
    }



    @GetMapping("updateUserName")
    @ApiOperation(value = "昵称修改")
    @ApiImplicitParam(name = "userName",required = true,value = "新昵称")
    public Response updateUserName(String userName, @ApiIgnore AppCurrentUser user){
        return Response.success( appPersonalService.updateUserName(userName,user));
    }


    @ApiOperation("头像大小5M 限制PNG JPG")
    @PostMapping("updateHeadImg")
    public Response updateHeadImg(@RequestParam("file") MultipartFile file,@ApiIgnore AppCurrentUser user) {
        String originalFilename = file.getOriginalFilename();
        String sub = StrUtil.subAfter(originalFilename, '.', true);
        if ( !ReUtil.isMatch("(jpg|png)",sub)){
            return Response.fail(ResponseCode.C_500101);
        }
        return getResponse(file,sub,uploadImgFile,user);
    }

    @PostMapping("updateUserPayPwd")
    @ApiOperation("设置支付密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "verifyCode",required = true,value = "验证码"),
            @ApiImplicitParam(name = "payWord",required = true,value = "新支付密码")
     })
    public Response updateUserPayPwd(@RequestBody UpdatePayPwdQuery query,@ApiIgnore AppCurrentUser user){
        query.setCellPhoneNum(user.getCellPhoneNum());
        return Response.success(appPersonalService.updateUserPayPwd(query));
    }



    @GetMapping("sendPayCode")
    @ApiOperation("获取验证码")
    public Response<Boolean> sendPayCode(String mobile,@ApiIgnore AppCurrentUser user){
        appPersonalService.sendPayCode(user.getCellPhoneNum());
        return Response.success(true);
    }














    /**
     * 上传图片服务器  返回图片地址
     * @param file
     * @param sub
     * @param path
     * @return
     */
    private Response getResponse(MultipartFile file, String sub,String path,AppCurrentUser user) {
        //按时间生成目录
        String date = StrUtil.removeAll(LocalDate.now().toString(), "-");
        String dir=base+path + File.separator +date+File.separator;
        File localFile = new File(dir);
        try {
            if (!localFile.exists()) {
                localFile.mkdirs();
            }
            //重新生成文件名
            String fileName= IdUtil.fastSimpleUUID()+"."+sub;
            //访问网站的路径
            String returnPath=path+"/"+date+"/"+fileName;
            String filePath=dir+ fileName;
            localFile=new File(filePath);
            if (!localFile.exists()){
                file.transferTo(localFile);
                //设置可读权限
                localFile.setReadable(true,false);
                localFile.setExecutable(true,false);
                localFile.setWritable(true,false);
                //保存路径到用户表
                appPersonalService.updateHeadImg(returnPath,user);
                return Response.success(returnPath);
            }
        } catch (Exception e) {
            log.error("修改头像发生异常",e);
            return Response.fail(ResponseCode.C_500100,"修改头像失败");
        }
        return Response.fail(ResponseCode.C_500100);
    }
}
