package com.dtdhehe.regist.entity;

import lombok.Data;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/7 16:22
 * @description
 **/
@Data
public class User {

    private Long id;
    private String username;
    private String password;
    private String nickName;
    private Integer age;
    private String phone;

}
