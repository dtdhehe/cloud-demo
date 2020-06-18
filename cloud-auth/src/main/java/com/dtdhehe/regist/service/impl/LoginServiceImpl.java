package com.dtdhehe.regist.service.impl;

import com.dtdhehe.common.util.BCryptUtils;
import com.dtdhehe.common.util.JwtUtils;
import com.dtdhehe.common.vo.ResultVO;
import com.dtdhehe.regist.entity.User;
import com.dtdhehe.regist.feign.UserFeign;
import com.dtdhehe.regist.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/8 16:57
 * @description
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserFeign userFeign;

    @Override
    public Map<String,Object> access(User user) {
        Map<String,Object> resultMap = new HashMap<>(8);
        ResultVO<User> result = userFeign.findByUsername(user.getUsername());
        User dbUser = result.getData();
        boolean isAccess = false;
        if (dbUser != null){
            isAccess = true;
            boolean check = BCryptUtils.check(user.getPassword(), dbUser.getPassword());
            //校验密码
            if (!check){
                //密码错误
                isAccess = false;
            }else {
                //获取token
                //自定义Claims
                Map<String,Object> claimsMap = new HashMap<>(16);

                String token = JwtUtils.createJwt(dbUser.getId().toString(), dbUser.getUsername(), claimsMap);
                resultMap.put("token",token);
            }
        }
        resultMap.put("isAccess",isAccess);
        return resultMap;
    }

}
