package com.module.admin.back.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限tree
 *
 * @author wangdong
 * @date 2019/5/23
 */
@Data
public class PermissionTreeResult implements Serializable {
    private String id;
    private String url;
    private String title;
    private String parentId;
    private String checkArr;
}
