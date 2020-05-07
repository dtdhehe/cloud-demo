package com.dtdhehe.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dtdhehe.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/7 14:24
 * @description
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
