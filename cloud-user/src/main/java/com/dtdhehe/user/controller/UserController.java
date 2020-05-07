package com.dtdhehe.user.controller;

import com.dtdhehe.common.vo.ResultVO;
import com.dtdhehe.user.entity.User;
import com.dtdhehe.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/7 14:35
 * @description
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResultVO addUser(@RequestBody User user){
        boolean result = userService.save(user);
        return result ? ResultVO.success("保存成功") : ResultVO.failed("保存失败");
    }

    @GetMapping("/{id}")
    public ResultVO findById(@PathVariable Long id){
        User user = userService.findById(id);
        return ResultVO.success("查询成功",user);
    }

    @GetMapping("/username")
    public ResultVO findByUsername(@RequestParam("username") String username){
        User user = userService.findByUsername(username);
        return ResultVO.success("查询成功",user);
    }

    @GetMapping("/phone")
    public ResultVO findByPhone(@RequestParam("phone") String phone){
        User user = userService.findByPhone(phone);
        return ResultVO.success("查询成功",user);
    }

}
