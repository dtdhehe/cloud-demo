package com.dtdhehe.common.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/8 16:31
 * @description
 **/
public class BCryptUtils {

    /**
     * 密码加密
     * @param password
     * @return
     */
    public static String encode(String password){
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    /**
     * 校验密码是否正确
     * @param password
     * @param hashPassword
     * @return
     */
    public static boolean check(String password,String hashPassword){
        return BCrypt.checkpw(password, hashPassword);
    }

}
