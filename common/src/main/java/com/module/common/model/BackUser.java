package com.module.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台用户
 *@author wangdong
 *@date 2019/5/10
 */
public class BackUser implements Serializable {
    /** 乐观锁 */
    private Integer revision ;
    /** 创建人 */
    private Integer createBy ;
    /** 创建时间 */
    private Date createTime ;
    /** 更新人 */
    private Integer updateBy ;
    /** 更新时间 */
    private Date updateTime ;
    /** 用户ID */
    private Integer id ;
    /** 用户名 */
    private String username ;
    /** 密码 */
    private String password ;
    /** 盐值;新建或修改时的随机字符串 */
    private String salt ;
    /** 是否锁定 */
    private Integer locked ;
    /** 手机号 */
    private String cellPhoneNum ;
    /** 加盐后的密码 */
    private String encryptPwd ;

    /** 乐观锁 */
    public Integer getRevision(){
        return this.revision;
    }
    /** 乐观锁 */
    public void setRevision(Integer revision){
        this.revision = revision;
    }
    /** 创建人 */
    public Integer getCreateBy(){
        return this.createBy;
    }
    /** 创建人 */
    public void setCreateBy(Integer createBy){
        this.createBy = createBy;
    }
    /** 创建时间 */
    public Date getCreateTime(){
        return this.createTime;
    }
    /** 创建时间 */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    /** 更新人 */
    public Integer getUpdateBy(){
        return this.updateBy;
    }
    /** 更新人 */
    public void setUpdateBy(Integer updateBy){
        this.updateBy = updateBy;
    }
    /** 更新时间 */
    public Date getUpdateTime(){
        return this.updateTime;
    }
    /** 更新时间 */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    /** 用户ID */
    public Integer getId(){
        return this.id;
    }
    /** 用户ID */
    public void setId(Integer id){
        this.id = id;
    }
    /** 用户名 */
    public String getUsername(){
        return this.username;
    }
    /** 用户名 */
    public void setUsername(String username){
        this.username = username;
    }
    /** 密码 */
    public String getPassword(){
        return this.password;
    }
    /** 密码 */
    public void setPassword(String password){
        this.password = password;
    }
    /** 盐值;新建或修改时的随机字符串 */
    public String getSalt(){
        return this.salt;
    }
    /** 盐值;新建或修改时的随机字符串 */
    public void setSalt(String salt){
        this.salt = salt;
    }
    /** 是否锁定 */
    public Integer getLocked(){
        return this.locked;
    }
    /** 是否锁定 */
    public void setLocked(Integer locked){
        this.locked = locked;
    }
    /** 手机号 */
    public String getCellPhoneNum(){
        return this.cellPhoneNum;
    }
    /** 手机号 */
    public void setCellPhoneNum(String cellPhoneNum){
        this.cellPhoneNum = cellPhoneNum;
    }
    /** 加盐后的密码 */
    public String getEncryptPwd(){
        return this.encryptPwd;
    }
    /** 加盐后的密码 */
    public void setEncryptPwd(String encryptPwd){
        this.encryptPwd = encryptPwd;
    }
}
