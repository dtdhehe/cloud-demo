package com.dtdhehe.auth.controller;

import com.dtdhehe.common.vo.ResultVO;
import com.dtdhehe.auth.entity.User;
import com.dtdhehe.auth.service.RegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/7 16:23
 * @description
 **/
@RestController
@RequestMapping("/regist")
public class RegistController {

    @Autowired
    private RegistService registService;

    @PostMapping("")
    public ResultVO regist(@RequestBody User user){
        return registService.regist(user);
    }

}
