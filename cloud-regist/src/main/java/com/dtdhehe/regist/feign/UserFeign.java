package com.dtdhehe.regist.feign;

import com.dtdhehe.common.vo.ResultVO;
import com.dtdhehe.regist.entity.User;
import com.dtdhehe.regist.feign.hystric.UserFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/7 16:19
 * @description
 **/
@FeignClient(value = "cloud-user",fallback = UserFeignHystrix.class)
public interface UserFeign {

    /**
     * 调用user-service的新增方法
     * @param user
     * @return
     */
    @PostMapping("/user")
    ResultVO addUser(@RequestBody User user);

    /**
     * 调用user-service的根据username查找用户方法
     * @param username
     * @return
     */
    @GetMapping("/user/username")
    ResultVO<User> findByUsername(@RequestParam("username") String username);

}
