package com.dtdhehe.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/6 16:39
 * @description
 **/
@Data
@TableName("tb_user")
public class User implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String nickName;
    private Integer age;
    private String phone;

}
