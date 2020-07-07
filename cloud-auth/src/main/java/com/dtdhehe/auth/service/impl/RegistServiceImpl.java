package com.dtdhehe.auth.service.impl;

import com.dtdhehe.common.util.BCryptUtils;
import com.dtdhehe.common.vo.ResultVO;
import com.dtdhehe.auth.entity.User;
import com.dtdhehe.auth.feign.UserFeign;
import com.dtdhehe.auth.service.RegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/7 16:56
 * @description
 **/
@Service
public class RegistServiceImpl implements RegistService {

    @Autowired
    private UserFeign userFeign;

    @Override
    public ResultVO regist(User user) {
        user.setPassword(BCryptUtils.encode(user.getPassword()));
        return userFeign.addUser(user);
    }
}
