package com.dtdhehe.auth.feign.hystric;

import com.dtdhehe.common.vo.ResultVO;
import com.dtdhehe.auth.entity.User;
import com.dtdhehe.auth.feign.UserFeign;
import org.springframework.stereotype.Component;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/7 16:44
 * @description
 **/
@Component
public class UserFeignHystrix implements UserFeign {

    @Override
    public ResultVO addUser(User user) {
        return ResultVO.failed("服务不可用，新增失败！");
    }

    @Override
    public ResultVO findByUsername(String username) {
        return ResultVO.failed("服务不可用，查询失败！");
    }
}
