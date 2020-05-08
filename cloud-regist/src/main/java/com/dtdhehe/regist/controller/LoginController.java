package com.dtdhehe.regist.controller;

import com.dtdhehe.common.vo.ResultVO;
import com.dtdhehe.regist.entity.User;
import com.dtdhehe.regist.service.LoginService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/8 16:47
 * @description
 **/
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("")
    public ResultVO login(@RequestBody User user){
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
            return ResultVO.failed("用户名或密码不能为空");
        }
        Map<String,Object> resultMap = loginService.access(user);
        boolean isAccess = (boolean) resultMap.get("isAccess");
        if (!isAccess){
            //校验失败
            return ResultVO.failed("用户名或密码错误");
        }
        return ResultVO.success("登录成功",resultMap.get("token"));
    }

}
