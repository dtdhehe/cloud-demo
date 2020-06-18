package com.dtdhehe.regist.service;

import com.dtdhehe.regist.entity.User;

import java.util.Map;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/8 16:54
 * @description
 **/
public interface LoginService {

    /**
     * 判断是否验证通过
     * @param user
     * @return
     */
    Map<String,Object> access(User user);

}
