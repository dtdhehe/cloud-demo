package com.dtdhehe.auth.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/7 16:22
 * @description
 **/
@Data
public class User implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String nickName;
    private Integer age;
    private String phone;

}
