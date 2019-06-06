package com.module.admin.back.result;

import com.module.admin.back.entity.BackUser;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class BackUserResult extends BackUser  implements Serializable {


    private String roleName;



}
