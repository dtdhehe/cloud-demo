package com.dtdhehe.auth.config;

import com.dtdhehe.auth.feign.UserFeign;
import com.dtdhehe.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/7/9 15:00
 * @description
 **/
@Component
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserFeign userFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResultVO<com.dtdhehe.auth.entity.User> resultVO = userFeign.findByUsername(username);
        com.dtdhehe.auth.entity.User dbUser = resultVO.getData();
        if (dbUser == null){
            throw new UsernameNotFoundException("查询失败");
        }
        return new User(username,dbUser.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
    }
}
