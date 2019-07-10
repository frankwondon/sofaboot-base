package com.api.app.controller.user;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.api.app.util.RequestUtil;
import com.module.api.app.entity.AppUser;
import com.module.api.app.query.CheckCodeQuery;
import com.module.api.app.query.LoginQuery;
import com.module.api.app.result.LoginResult;
import com.module.api.app.result.UserManagerResult;
import com.module.api.app.service.AppPersonalService;
import com.module.api.app.service.UserService;
import com.module.common.Response;
import com.module.common.ResponseCode;
import com.module.common.bean.AppCurrentUser;
import com.module.common.bean.AppTokenDto;
import com.module.common.constant.AppUserType;
import com.module.common.constant.HeaderConstant;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;

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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",required = true,value = "新昵称")
    })
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

    @PostMapping("setPayWord")
    @ApiOperation("设置支付密码")
    @ApiImplicitParam(dataType = "String",paramType="header",name = "x-token")
    public Response setPayWord(@RequestBody CheckCodeQuery query, HttpServletRequest request){
        //LoginResult login = userService.login(query);

        return Response.success(null);
    }



    @GetMapping("checkCode")
    @ApiOperation("获取验证码")
    public Response<Boolean> checkCode(String mobile){
        userService.sendLoginVerifyCode(mobile);
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
                localFile.setReadable(true);
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
