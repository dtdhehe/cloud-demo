package com.dtdhehe.regist.service;

import com.dtdhehe.common.vo.ResultVO;
import com.dtdhehe.regist.entity.User;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/7 16:55
 * @description
 **/
public interface RegistService {

    /**
     * 注册用户
     * @param user
     * @return
     */
    ResultVO regist(User user);

}
