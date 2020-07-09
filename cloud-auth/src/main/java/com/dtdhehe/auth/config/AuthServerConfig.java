package com.dtdhehe.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xie_东
 * @version 1.0.0
 * @date 2020/7/7 17:40
 * @description
 **/
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String JWT_TOKEN_KEY = "dtdhehe";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenEnhancer jwtTokenEnhancer;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("app1")
                .secret(new BCryptPasswordEncoder().encode("app1"))
//                .resourceIds("1")
                //授权码、密码、刷新
                .authorizedGrantTypes("authorization_code","password","refresh_token")
                .scopes("app");
    }

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(JWT_TOKEN_KEY);
        return converter;
    }

    @Bean
    public TokenEnhancerChain initTokenEnhancerChain(){
        TokenEnhancerChain tokenEnhancerChain=new TokenEnhancerChain();

        List<TokenEnhancer> list=new ArrayList<>();
        //添加自定义tokenEnhancer
        list.add(jwtTokenEnhancer);
        //将token转换为jwt
        list.add(accessTokenConverter());
        tokenEnhancerChain.setTokenEnhancers(list);

        return tokenEnhancerChain;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                    .tokenStore(tokenStore())
                    .accessTokenConverter(accessTokenConverter())
                    .tokenEnhancer(initTokenEnhancerChain());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }
}
