package com.dtdhehe.user.service;

import com.dtdhehe.user.entity.User;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/7 14:20
 * @description
 **/
public interface UserService {

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据用户手机号码查找用户
     * @param phone
     * @return
     */
    User findByPhone(String phone);

    /**
     * 新增用户
     * @param user
     * @return
     */
    boolean save(User user);
}
