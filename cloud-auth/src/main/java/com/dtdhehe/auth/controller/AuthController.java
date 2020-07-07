package com.dtdhehe.auth.controller;

import com.dtdhehe.common.vo.ResultVO;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/7/1 17:39
 * @description
 **/
@RestController
@RequestMapping("/auth")
public class AuthController {


    @GetMapping("/me")
    public ResultVO me(Authentication authentication){
        return ResultVO.success("查询成功",authentication.getPrincipal());
    }


    @GetMapping("/hello")
    public ResultVO me( ){
        return ResultVO.success("查询成功","hello");
    }

}
