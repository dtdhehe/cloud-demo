package com.dtdhehe.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dtdhehe.common.util.IdWoker;
import com.dtdhehe.user.entity.User;
import com.dtdhehe.user.mapper.UserMapper;
import com.dtdhehe.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/5/7 14:22
 * @description
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private IdWoker idWoker;
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.SUPPORTS)
    public User findById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.SUPPORTS)
    public User findByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.SUPPORTS)
    public User findByPhone(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public boolean save(User user) {
        user.setId(idWoker.nextId());
        int result = userMapper.insert(user);
        return result>0;
    }
}
